package com.carsense.ai.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carsense.ai.device.connection.bluetooth.BluetoothController
import com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain
import com.carsense.ai.device.connection.bluetooth.ConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BluetoothUiState(
    val isScanning: Boolean = false,
    val connectionState: ConnectionState = ConnectionState.Disconnected,
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
) : ViewModel() {

    private val _uiState = MutableStateFlow(BluetoothUiState())
    val uiState: StateFlow<BluetoothUiState> = _uiState.asStateFlow()

    init {
        // Observe scanned devices
        viewModelScope.launch {
            bluetoothController.scannedDevices.collect { devices ->
                _uiState.value = _uiState.value.copy(scannedDevices = devices)
            }
        }
        
        // Observe connection state
        viewModelScope.launch {
            bluetoothController.connectionState.collect { state ->
                _uiState.value = _uiState.value.copy(connectionState = state)
            }
        }
    }

    fun startScan() {
        _uiState.value = _uiState.value.copy(isScanning = true)
        bluetoothController.startDiscovery()
    }

    fun stopScan() {
        _uiState.value = _uiState.value.copy(isScanning = false)
        bluetoothController.stopDiscovery()
    }

    fun connect(device: BluetoothDeviceDomain) {
        viewModelScope.launch {
            bluetoothController.connectToDevice(device).collect { state ->
                // The connection state is also emitted by the main connectionState flow,
                // but you can also handle specifics here if needed.
            }
        }
    }
    
    fun disconnect() {
        bluetoothController.disconnect()
    }
}
