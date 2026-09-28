package com.carsense.ai.device.connection.bluetooth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0.2\u0006\u0010/\u001a\u00020\fH\u0016J\b\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u000201H\u0016J\b\u00107\u001a\u000201H\u0016J\b\u00108\u001a\u000201H\u0016J\b\u00109\u001a\u000201H\u0002J\u0016\u0010:\u001a\u00020\f*\u00020;2\b\b\u0002\u0010<\u001a\u00020=H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u0015\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\"\u001a\u0004\u0018\u00010#@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010\'\u001a\u00020(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u001d8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001fR \u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u001d8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001f\u00a8\u0006>"}, d2 = {"Lcom/carsense/ai/device/connection/bluetooth/AndroidBluetoothController;", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothController;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "SPP_UUID", "Ljava/util/UUID;", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/carsense/ai/device/connection/bluetooth/ConnectionState;", "_pairedDevices", "", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDeviceDomain;", "_scannedDevices", "bleScanCallback", "Landroid/bluetooth/le/ScanCallback;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "getBluetoothAdapter", "()Landroid/bluetooth/BluetoothAdapter;", "bluetoothAdapter$delegate", "Lkotlin/Lazy;", "bluetoothManager", "Landroid/bluetooth/BluetoothManager;", "kotlin.jvm.PlatformType", "getBluetoothManager", "()Landroid/bluetooth/BluetoothManager;", "bluetoothManager$delegate", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "currentClientSocket", "Landroid/bluetooth/BluetoothSocket;", "<set-?>", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDataTransferService;", "dataTransferService", "getDataTransferService", "()Lcom/carsense/ai/device/connection/bluetooth/BluetoothDataTransferService;", "foundDeviceReceiver", "Landroid/content/BroadcastReceiver;", "pairedDevices", "getPairedDevices", "scannedDevices", "getScannedDevices", "connectToDevice", "Lkotlinx/coroutines/flow/Flow;", "device", "disconnect", "", "hasPermission", "", "permission", "", "release", "startDiscovery", "stopDiscovery", "updatePairedDevices", "toBluetoothDeviceDomain", "Landroid/bluetooth/BluetoothDevice;", "deviceType", "Lcom/carsense/ai/device/connection/bluetooth/DeviceType;", "app_debug"})
@android.annotation.SuppressLint(value = {"MissingPermission"})
public final class AndroidBluetoothController implements com.carsense.ai.device.connection.bluetooth.BluetoothController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy bluetoothManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy bluetoothAdapter$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> _scannedDevices = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> _pairedDevices = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.carsense.ai.device.connection.bluetooth.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.Nullable()
    private android.bluetooth.BluetoothSocket currentClientSocket;
    @org.jetbrains.annotations.Nullable()
    private com.carsense.ai.device.connection.bluetooth.BluetoothDataTransferService dataTransferService;
    @org.jetbrains.annotations.NotNull()
    private final java.util.UUID SPP_UUID = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver foundDeviceReceiver = null;
    @org.jetbrains.annotations.NotNull()
    private final android.bluetooth.le.ScanCallback bleScanCallback = null;
    
    public AndroidBluetoothController(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.bluetooth.BluetoothManager getBluetoothManager() {
        return null;
    }
    
    private final android.bluetooth.BluetoothAdapter getBluetoothAdapter() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> getScannedDevices() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain>> getPairedDevices() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.carsense.ai.device.connection.bluetooth.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.carsense.ai.device.connection.bluetooth.BluetoothDataTransferService getDataTransferService() {
        return null;
    }
    
    private final void updatePairedDevices() {
    }
    
    @java.lang.Override()
    public void startDiscovery() {
    }
    
    @java.lang.Override()
    public void stopDiscovery() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.carsense.ai.device.connection.bluetooth.ConnectionState> connectToDevice(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain device) {
        return null;
    }
    
    @java.lang.Override()
    public void disconnect() {
    }
    
    @java.lang.Override()
    public void release() {
    }
    
    private final boolean hasPermission(java.lang.String permission) {
        return false;
    }
    
    private final com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain toBluetoothDeviceDomain(android.bluetooth.BluetoothDevice $this$toBluetoothDeviceDomain, com.carsense.ai.device.connection.bluetooth.DeviceType deviceType) {
        return null;
    }
}