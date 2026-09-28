package com.carsense.ai.device.connection.wifi

import kotlinx.coroutines.flow.Flow

interface WifiDataTransferService {
    fun listenForIncomingMessages(): Flow<String>
    suspend fun sendMessage(bytes: ByteArray): Boolean
}
