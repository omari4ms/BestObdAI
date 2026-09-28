package com.carsense.ai.device.permissions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u001a\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u001a\u000e\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\r"}, d2 = {"AppPermissionsHandler", "", "onAllPermissionsGranted", "Lkotlin/Function0;", "checkAllPermissionsGranted", "", "context", "Landroid/content/Context;", "permissions", "", "", "getRequiredPermissions", "openAppSettings", "app_debug"})
public final class PermissionKt {
    
    /**
     * Returns a list of required runtime permissions based on the device's Android version.
     * Note: INTERNET is an install-time permission and does not need runtime request, 
     * but must be in AndroidManifest.xml.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<java.lang.String> getRequiredPermissions() {
        return null;
    }
    
    public static final boolean checkAllPermissionsGranted(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> permissions) {
        return false;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AppPermissionsHandler(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAllPermissionsGranted) {
    }
    
    public static final void openAppSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}