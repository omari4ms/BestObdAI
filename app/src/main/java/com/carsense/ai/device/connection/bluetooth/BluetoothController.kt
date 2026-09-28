package com.carsense.ai.device.connection.bluetooth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val pairedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val connectionState: StateFlow<ConnectionState>

    fun startDiscovery()
    fun stopDiscovery()
    fun connectToDevice(device: BluetoothDeviceDomain): Flow<ConnectionState>
    fun disconnect()
    fun release()
}
