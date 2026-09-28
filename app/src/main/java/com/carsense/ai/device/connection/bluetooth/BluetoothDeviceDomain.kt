package com.carsense.ai.device.connection.bluetooth

data class BluetoothDeviceDomain(
    val name: String?,
    val address: String,
    val deviceType: DeviceType = DeviceType.UNKNOWN
)

enum class DeviceType {
    CLASSIC,
    BLE,
    UNKNOWN
}
