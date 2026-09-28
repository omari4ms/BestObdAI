package com.carsense.ai.device.connection.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Build
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.util.UUID
import kotlin.collections.plus

@SuppressLint("MissingPermission")
class AndroidBluetoothController(
    private val context: Context
) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val _scannedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _scannedDevices.asStateFlow()

    private val _pairedDevices = MutableStateFlow<List<BluetoothDeviceDomain>>(emptyList())
    override val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
        get() = _pairedDevices.asStateFlow()

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    override val connectionState: StateFlow<ConnectionState>
        get() = _connectionState.asStateFlow()

    private var currentClientSocket: BluetoothSocket? = null
    var dataTransferService: BluetoothDataTransferService? = null
        private set

    // Standard SPP UUID for OBD devices
    private val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    private val foundDeviceReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    }
                    
                    device?.let {
                        val deviceDomain = it.toBluetoothDeviceDomain(DeviceType.CLASSIC)
                        if (deviceDomain.name != null && !_scannedDevices.value.any { d -> d.address == deviceDomain.address }) {
                            _scannedDevices.update { devices -> devices + deviceDomain }
                        }
                    }
                }
            }
        }
    }

    private val bleScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.device?.let { device ->
                // Suppress missing permission for device name on older APIs if needed, but we already check BLUETOOTH_SCAN/CONNECT
                val deviceDomain = device.toBluetoothDeviceDomain(DeviceType.BLE)
                if (deviceDomain.name != null && !_scannedDevices.value.any { d -> d.address == deviceDomain.address }) {
                    _scannedDevices.update { devices -> devices + deviceDomain }
                }
            }
        }
    }

    init {
        updatePairedDevices()
    }

    private fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) return
        
        bluetoothAdapter?.bondedDevices?.let { devices ->
            _pairedDevices.update { 
                devices.map { it.toBluetoothDeviceDomain() }
            }
        }
    }

    override fun startDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return

        // Register for broadcasts when a device is discovered.
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(foundDeviceReceiver, filter)

        updatePairedDevices()
        _scannedDevices.update { emptyList() }
        bluetoothAdapter?.startDiscovery()
        bluetoothAdapter?.bluetoothLeScanner?.startScan(bleScanCallback)
    }

    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) return
        bluetoothAdapter?.cancelDiscovery()
        bluetoothAdapter?.bluetoothLeScanner?.stopScan(bleScanCallback)
    }

    override fun connectToDevice(device: BluetoothDeviceDomain): Flow<ConnectionState> = flow {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            emit(ConnectionState.Error("Missing Bluetooth Connect Permission"))
            return@flow
        }

        val bluetoothDevice = bluetoothAdapter?.getRemoteDevice(device.address)
        if (bluetoothDevice == null) {
            emit(ConnectionState.Error("Device not found"))
            return@flow
        }

        emit(ConnectionState.Connecting)
        _connectionState.update { ConnectionState.Connecting }

        // Cancel discovery because it otherwise slows down the connection.
        bluetoothAdapter?.cancelDiscovery()

        currentClientSocket = bluetoothDevice.createRfcommSocketToServiceRecord(SPP_UUID)

        try {
            currentClientSocket?.connect()
            dataTransferService = AndroidBluetoothDataTransferService(currentClientSocket!!)
            
            emit(ConnectionState.Connected)
            _connectionState.update { ConnectionState.Connected }
        } catch (e: IOException) {
            currentClientSocket?.close()
            currentClientSocket = null
            emit(ConnectionState.Error("Connection failed: ${e.message}"))
            _connectionState.update { ConnectionState.Error("Connection failed: ${e.message}") }
        }
    }.flowOn(Dispatchers.IO)

    override fun disconnect() {
        try {
            currentClientSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        currentClientSocket = null
        dataTransferService = null
        _connectionState.update { ConnectionState.Disconnected }
    }

    override fun release() {
        try {
            context.unregisterReceiver(foundDeviceReceiver)
        } catch (e: IllegalArgumentException) {
            // Receiver not registered
        }
        disconnect()
    }

    private fun hasPermission(permission: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S && permission == Manifest.permission.BLUETOOTH_CONNECT) return true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S && permission == Manifest.permission.BLUETOOTH_SCAN) return true
        
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun BluetoothDevice.toBluetoothDeviceDomain(deviceType: DeviceType = DeviceType.CLASSIC): BluetoothDeviceDomain {
        return BluetoothDeviceDomain(
            name = name,
            address = address,
            deviceType = deviceType
        )
    }
}
