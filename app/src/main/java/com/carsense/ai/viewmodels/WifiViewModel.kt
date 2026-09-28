package com.carsense.ai.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carsense.ai.device.connection.wifi.WifiController
import com.carsense.ai.device.connection.wifi.WifiConnectionState
import com.carsense.ai.device.connection.wifi.WifiNetworkDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WifiUiState(
    val connectionState: WifiConnectionState = WifiConnectionState.Disconnected,
    val scannedNetworks: List<WifiNetworkDomain> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class WifiViewModel @Inject constructor(
    private val wifiController: WifiController
) : ViewModel() {

    private val _uiState = MutableStateFlow(WifiUiState())
    val uiState: StateFlow<WifiUiState> = _uiState.asStateFlow()

    init {
        // Observe scanned networks
        viewModelScope.launch {
            wifiController.scannedNetworks.collect { networks ->
                _uiState.value = _uiState.value.copy(scannedNetworks = networks)
            }
        }
        
        // Observe connection state
        viewModelScope.launch {
            wifiController.connectionState.collect { state ->
                _uiState.value = _uiState.value.copy(connectionState = state)
            }
        }
    }
    
    fun startScan() {
        wifiController.startScan()
    }

    fun connectToNetwork(ssid: String, password: String? = null) {
        viewModelScope.launch {
            wifiController.connectToNetwork(ssid, password).collect { state ->
                // Handled globally by the connectionState flow, but available here too
            }
        }
    }
    
    fun disconnect() {
        wifiController.disconnect()
    }
}
