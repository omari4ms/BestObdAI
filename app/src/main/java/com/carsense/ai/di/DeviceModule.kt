package com.carsense.ai.di

import android.content.Context
import com.carsense.ai.device.connection.bluetooth.AndroidBluetoothController
import com.carsense.ai.device.connection.bluetooth.BluetoothController
import com.carsense.ai.device.connection.wifi.AndroidWifiController
import com.carsense.ai.device.connection.wifi.WifiController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeviceModule {

    @Provides
    @Singleton
    fun provideBluetoothController(
        @ApplicationContext context: Context
    ): BluetoothController {
        return AndroidBluetoothController(context)
    }

    @Provides
    @Singleton
    fun provideWifiController(
        @ApplicationContext context: Context
    ): WifiController {
        return AndroidWifiController(context)
    }
}
