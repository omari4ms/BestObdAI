package com.carsense.ai.device.connection.bluetooth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/carsense/ai/device/connection/bluetooth/AndroidBluetoothDataTransferService;", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDataTransferService;", "socket", "Landroid/bluetooth/BluetoothSocket;", "(Landroid/bluetooth/BluetoothSocket;)V", "listenForIncomingMessages", "Lkotlinx/coroutines/flow/Flow;", "", "sendMessage", "", "bytes", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AndroidBluetoothDataTransferService implements com.carsense.ai.device.connection.bluetooth.BluetoothDataTransferService {
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.BluetoothSocket socket = null;
    
    public AndroidBluetoothDataTransferService(@org.jetbrains.annotations.NotNull()
    android.bluetooth.BluetoothSocket socket) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.String> listenForIncomingMessages() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}