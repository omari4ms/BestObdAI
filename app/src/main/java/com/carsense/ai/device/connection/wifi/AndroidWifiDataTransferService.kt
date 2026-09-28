package com.carsense.ai.device.connection.wifi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.Socket

class AndroidWifiDataTransferService(
    private val socket: Socket
) : WifiDataTransferService {

    override fun listenForIncomingMessages(): Flow<String> = flow {
        if (!socket.isConnected || socket.isClosed) {
            return@flow
        }
        val buffer = ByteArray(1024)
        while (true) {
            val byteCount = try {
                socket.getInputStream().read(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
            if (byteCount > 0) {
                // Usually OBD devices send ASCII or UTF-8
                val message = String(buffer, 0, byteCount)
                emit(message)
            } else if (byteCount == -1) {
                // Stream closed
                break
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun sendMessage(bytes: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (socket.isConnected && !socket.isClosed) {
                    socket.getOutputStream().write(bytes)
                    socket.getOutputStream().flush()
                    true
                } else {
                    false
                }
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
    }
}
