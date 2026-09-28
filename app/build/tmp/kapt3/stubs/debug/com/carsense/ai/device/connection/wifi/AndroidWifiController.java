package com.carsense.ai.device.connection.wifi;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0,2\u0006\u0010-\u001a\u00020\u00062\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0006H\u0002J\b\u00104\u001a\u000200H\u0016J\b\u00105\u001a\u000200H\u0002J\b\u00106\u001a\u000200H\u0002J\b\u00107\u001a\u000200H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00108VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u0012R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\u0018\u001a\u0004\b&\u0010\'R\u000e\u0010)\u001a\u00020*X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"}, d2 = {"Lcom/carsense/ai/device/connection/wifi/AndroidWifiController;", "Lcom/carsense/ai/device/connection/wifi/WifiController;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "OBD_IP", "", "OBD_PORT", "", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/carsense/ai/device/connection/wifi/WifiConnectionState;", "_scannedNetworks", "", "Lcom/carsense/ai/device/connection/wifi/WifiNetworkDomain;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "connectivityManager", "Landroid/net/ConnectivityManager;", "getConnectivityManager", "()Landroid/net/ConnectivityManager;", "connectivityManager$delegate", "Lkotlin/Lazy;", "currentSocket", "Ljava/net/Socket;", "<set-?>", "Lcom/carsense/ai/device/connection/wifi/WifiDataTransferService;", "dataTransferService", "getDataTransferService", "()Lcom/carsense/ai/device/connection/wifi/WifiDataTransferService;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "scannedNetworks", "getScannedNetworks", "wifiManager", "Landroid/net/wifi/WifiManager;", "getWifiManager", "()Landroid/net/wifi/WifiManager;", "wifiManager$delegate", "wifiScanReceiver", "Landroid/content/BroadcastReceiver;", "connectToNetwork", "Lkotlinx/coroutines/flow/Flow;", "ssid", "password", "disconnect", "", "hasPermission", "", "permission", "release", "scanFailure", "scanSuccess", "startScan", "app_debug"})
@android.annotation.SuppressLint(value = {"MissingPermission"})
public final class AndroidWifiController implements com.carsense.ai.device.connection.wifi.WifiController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy wifiManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy connectivityManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.carsense.ai.device.connection.wifi.WifiNetworkDomain>> _scannedNetworks = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.carsense.ai.device.connection.wifi.WifiConnectionState> _connectionState = null;
    @org.jetbrains.annotations.Nullable()
    private java.net.Socket currentSocket;
    @org.jetbrains.annotations.Nullable()
    private com.carsense.ai.device.connection.wifi.WifiDataTransferService dataTransferService;
    @org.jetbrains.annotations.Nullable()
    private android.net.ConnectivityManager.NetworkCallback networkCallback;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String OBD_IP = "192.168.0.10";
    private final int OBD_PORT = 35000;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver wifiScanReceiver = null;
    
    public AndroidWifiController(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.net.wifi.WifiManager getWifiManager() {
        return null;
    }
    
    private final android.net.ConnectivityManager getConnectivityManager() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<java.util.List<com.carsense.ai.device.connection.wifi.WifiNetworkDomain>> getScannedNetworks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.carsense.ai.device.connection.wifi.WifiConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.carsense.ai.device.connection.wifi.WifiDataTransferService getDataTransferService() {
        return null;
    }
    
    private final void scanSuccess() {
    }
    
    private final void scanFailure() {
    }
    
    @java.lang.Override()
    public void startScan() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.carsense.ai.device.connection.wifi.WifiConnectionState> connectToNetwork(@org.jetbrains.annotations.NotNull()
    java.lang.String ssid, @org.jetbrains.annotations.Nullable()
    java.lang.String password) {
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
}