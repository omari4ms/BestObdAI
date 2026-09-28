package com.carsense.ai.device.connection.wifi

sealed interface WifiConnectionState {
    object Disconnected : WifiConnectionState
    object Connecting : WifiConnectionState
    object Connected : WifiConnectionState
    data class Error(val message: String) : WifiConnectionState
}
