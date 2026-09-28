package com.carsense.ai.device.connection.wifi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface WifiController {
    val scannedNetworks: StateFlow<List<WifiNetworkDomain>>
    val connectionState: StateFlow<WifiConnectionState>

    fun startScan()
    fun connectToNetwork(ssid: String, password: String? = null): Flow<WifiConnectionState>
    fun disconnect()
    fun release()
}
