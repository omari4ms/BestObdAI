package com.carsense.ai.device.connection.bluetooth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u000f\u001a\u00020\tH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0011H&J\b\u0010\u0013\u001a\u00020\u0011H&J\b\u0010\u0014\u001a\u00020\u0011H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u001e\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\u0015"}, d2 = {"Lcom/carsense/ai/device/connection/bluetooth/BluetoothController;", "", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/carsense/ai/device/connection/bluetooth/ConnectionState;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "pairedDevices", "", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDeviceDomain;", "getPairedDevices", "scannedDevices", "getScannedDevices", "connectToDevice", "Lkotlinx/coroutines/flow/Flow;", "device", "disconnect", "", "release", "startDiscovery", "stopDiscovery", "app_debug"})
public abstract interface BluetoothController {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> getScannedDevices();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> getPairedDevices();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<com.carsense.ai.device.connection.bluetooth.ConnectionState> getConnectionState();
    
    public abstract void startDiscovery();
    
    public abstract void stopDiscovery();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.carsense.ai.device.connection.bluetooth.ConnectionState> connectToDevice(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain device);
    
    public abstract void disconnect();
    
    public abstract void release();
}