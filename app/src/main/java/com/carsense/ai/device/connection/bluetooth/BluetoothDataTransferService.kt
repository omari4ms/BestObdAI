package com.carsense.ai.device.connection.bluetooth

import kotlinx.coroutines.flow.Flow

interface BluetoothDataTransferService {
    fun listenForIncomingMessages(): Flow<String>
    suspend fun sendMessage(bytes: ByteArray): Boolean
}
