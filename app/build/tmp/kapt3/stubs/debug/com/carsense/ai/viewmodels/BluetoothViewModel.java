package com.carsense.ai.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\rJ\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0013"}, d2 = {"Lcom/carsense/ai/viewmodels/BluetoothViewModel;", "Landroidx/lifecycle/ViewModel;", "bluetoothController", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothController;", "(Lcom/carsense/ai/device/connection/bluetooth/BluetoothController;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/carsense/ai/viewmodels/BluetoothUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "connect", "", "device", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDeviceDomain;", "disconnect", "startScan", "stopScan", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BluetoothViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.carsense.ai.device.connection.bluetooth.BluetoothController bluetoothController = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.carsense.ai.viewmodels.BluetoothUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.carsense.ai.viewmodels.BluetoothUiState> uiState = null;
    
    @javax.inject.Inject()
    public BluetoothViewModel(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.device.connection.bluetooth.BluetoothController bluetoothController) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.carsense.ai.viewmodels.BluetoothUiState> getUiState() {
        return null;
    }
    
    public final void startScan() {
    }
    
    public final void stopScan() {
    }
    
    public final void connect(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain device) {
    }
    
    public final void disconnect() {
    }
}