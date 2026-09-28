package com.carsense.ai.device.connection.wifi

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.Socket

@SuppressLint("MissingPermission")
class AndroidWifiController(
    private val context: Context
) : WifiController {

    private val wifiManager by lazy {
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }
    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val _scannedNetworks = MutableStateFlow<List<WifiNetworkDomain>>(emptyList())
    override val scannedNetworks: StateFlow<List<WifiNetworkDomain>>
        get() = _scannedNetworks.asStateFlow()

    private val _connectionState = MutableStateFlow<WifiConnectionState>(WifiConnectionState.Disconnected)
    override val connectionState: StateFlow<WifiConnectionState>
        get() = _connectionState.asStateFlow()

    private var currentSocket: Socket? = null
    var dataTransferService: WifiDataTransferService? = null
        private set

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    // Default OBD-II Dongle IP and Port
    private val OBD_IP = "192.168.0.10"
    private val OBD_PORT = 35000

    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    private fun scanSuccess() {
        if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) return
        val results = wifiManager.scanResults
        _scannedNetworks.update { 
            results.map { result ->
                WifiNetworkDomain(
                    ssid = result.SSID ?: "Unknown",
                    bssid = result.BSSID
                )
            }.filter { it.ssid.isNotEmpty() && it.ssid != "Unknown" }
        }
    }

    private fun scanFailure() {
        // Handle failure (e.g., use older cached results)
        scanSuccess()
    }

    override fun startScan() {
        if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) return
        
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanReceiver, intentFilter)
        
        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        }
    }

    override fun connectToNetwork(ssid: String, password: String?): Flow<WifiConnectionState> = flow {
        emit(WifiConnectionState.Connecting)
        _connectionState.update { WifiConnectionState.Connecting }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val specifierBuilder = WifiNetworkSpecifier.Builder().setSsid(ssid)
            if (!password.isNullOrEmpty()) {
                specifierBuilder.setWpa2Passphrase(password)
            }
            
            val request = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) // Dongles usually don't have internet
                .setNetworkSpecifier(specifierBuilder.build())
                .build()

            var networkBound = false

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    // Bind app to this network so socket uses WiFi, not Cellular
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        connectivityManager.bindProcessToNetwork(network)
                    }
                    
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            currentSocket = network.socketFactory.createSocket(OBD_IP, OBD_PORT)
                            dataTransferService = AndroidWifiDataTransferService(currentSocket!!)
                            _connectionState.update { WifiConnectionState.Connected }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            _connectionState.update { WifiConnectionState.Error("Socket connection failed: ${e.message}") }
                        }
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    _connectionState.update { WifiConnectionState.Error("Network unavailable") }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    disconnect()
                }
            }

            connectivityManager.requestNetwork(request, networkCallback!!)
            
        } else {
            // Legacy Android (< API 29) connection would go here, 
            // utilizing WifiManager.addNetwork() and WifiManager.enableNetwork()
            emit(WifiConnectionState.Error("Android versions below 10 are not fully supported in this flow yet."))
        }
    }.flowOn(Dispatchers.IO)

    override fun disconnect() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.bindProcessToNetwork(null)
        }
        
        networkCallback?.let {
            connectivityManager.unregisterNetworkCallback(it)
            networkCallback = null
        }
        
        try {
            currentSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        currentSocket = null
        dataTransferService = null
        _connectionState.update { WifiConnectionState.Disconnected }
    }

    override fun release() {
        try {
            context.unregisterReceiver(wifiScanReceiver)
        } catch (e: IllegalArgumentException) {
            // Receiver not registered
        }
        disconnect()
    }

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}
