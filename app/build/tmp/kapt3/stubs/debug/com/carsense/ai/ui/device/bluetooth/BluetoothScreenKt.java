package com.carsense.ai.ui.device.bluetooth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001aH\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0016H\u0003\u001a,\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001c"}, d2 = {"BluetoothScreen", "", "viewModel", "Lcom/carsense/ai/viewmodels/BluetoothViewModel;", "DeviceItem", "name", "", "status", "statusColor", "Landroidx/compose/ui/graphics/Color;", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconTint", "onClick", "Lkotlin/Function0;", "DeviceItem-lrywsJo", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/graphics/vector/ImageVector;JLkotlin/jvm/functions/Function0;)V", "DeviceListSection", "devices", "", "Lcom/carsense/ai/device/connection/bluetooth/BluetoothDeviceDomain;", "onDeviceClick", "Lkotlin/Function1;", "ScanButton", "isScanning", "", "onStartScan", "onStopScan", "app_debug"})
public final class BluetoothScreenKt {
    
    @androidx.compose.ui.tooling.preview.Preview()
    @androidx.compose.runtime.Composable()
    public static final void BluetoothScreen(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.viewmodels.BluetoothViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ScanButton(boolean isScanning, kotlin.jvm.functions.Function0<kotlin.Unit> onStartScan, kotlin.jvm.functions.Function0<kotlin.Unit> onStopScan) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeviceListSection(java.util.List<com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain> devices, kotlin.jvm.functions.Function1<? super com.carsense.ai.device.connection.bluetooth.BluetoothDeviceDomain, kotlin.Unit> onDeviceClick) {
    }
}