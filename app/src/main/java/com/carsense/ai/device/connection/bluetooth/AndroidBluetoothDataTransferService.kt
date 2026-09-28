package com.carsense.ai.device.connection.bluetooth

import android.bluetooth.BluetoothSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class AndroidBluetoothDataTransferService(
    private val socket: BluetoothSocket
) : BluetoothDataTransferService {

    override fun listenForIncomingMessages(): Flow<String> = flow {
        if (!socket.isConnected) {
            return@flow
        }
        val buffer = ByteArray(1024)
        while (true) {
            val byteCount = try {
                socket.inputStream.read(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
                break
            }
            if (byteCount > 0) {
                // Usually OBD devices send ASCII or UTF-8
                val message = String(buffer, 0, byteCount)
                emit(message)
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun sendMessage(bytes: ByteArray): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (socket.isConnected) {
                    socket.outputStream.write(bytes)
                    socket.outputStream.flush()
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
