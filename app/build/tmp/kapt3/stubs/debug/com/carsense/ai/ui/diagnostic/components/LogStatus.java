package com.carsense.ai.ui.diagnostic.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/carsense/ai/ui/diagnostic/components/LogStatus;", "", "label", "", "colorId", "", "requiresPulse", "", "(Ljava/lang/String;ILjava/lang/String;IZ)V", "getColorId", "()I", "getLabel", "()Ljava/lang/String;", "getRequiresPulse", "()Z", "FIXED", "PENDING_FIX", "ALL_CLEAR", "CRITICAL_NOT_FIXED", "app_debug"})
public enum LogStatus {
    /*public static final*/ FIXED /* = new FIXED(null, 0, false) */,
    /*public static final*/ PENDING_FIX /* = new PENDING_FIX(null, 0, false) */,
    /*public static final*/ ALL_CLEAR /* = new ALL_CLEAR(null, 0, false) */,
    /*public static final*/ CRITICAL_NOT_FIXED /* = new CRITICAL_NOT_FIXED(null, 0, false) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    private final int colorId = 0;
    private final boolean requiresPulse = false;
    
    LogStatus(java.lang.String label, int colorId, boolean requiresPulse) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    public final int getColorId() {
        return 0;
    }
    
    public final boolean getRequiresPulse() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.carsense.ai.ui.diagnostic.components.LogStatus> getEntries() {
        return null;
    }
}