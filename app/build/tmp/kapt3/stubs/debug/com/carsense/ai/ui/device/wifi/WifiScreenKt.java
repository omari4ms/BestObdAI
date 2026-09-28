package com.carsense.ai.ui.device.wifi;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a*\u0010\r\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u0012H\u0003\u001a\u001e\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u0012\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"NetworkItem", "", "ssid", "", "signal", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconTint", "Landroidx/compose/ui/graphics/Color;", "onClick", "Lkotlin/Function0;", "NetworkItem-42QJj7c", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLkotlin/jvm/functions/Function0;)V", "NetworkListSection", "networks", "", "Lcom/carsense/ai/device/connection/wifi/WifiNetworkDomain;", "onNetworkClick", "Lkotlin/Function1;", "ScanButton", "isScanning", "", "onStartScan", "WifiScreen", "viewModel", "Lcom/carsense/ai/viewmodels/WifiViewModel;", "app_debug"})
public final class WifiScreenKt {
    
    @androidx.compose.ui.tooling.preview.Preview()
    @androidx.compose.runtime.Composable()
    public static final void WifiScreen(@org.jetbrains.annotations.NotNull()
    com.carsense.ai.viewmodels.WifiViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ScanButton(boolean isScanning, kotlin.jvm.functions.Function0<kotlin.Unit> onStartScan) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void NetworkListSection(java.util.List<com.carsense.ai.device.connection.wifi.WifiNetworkDomain> networks, kotlin.jvm.functions.Function1<? super com.carsense.ai.device.connection.wifi.WifiNetworkDomain, kotlin.Unit> onNetworkClick) {
    }
}