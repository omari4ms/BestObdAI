package com.carsense.ai.ui.diagnostic.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class LogStatus(val label: String, val colorId: Int, val requiresPulse: Boolean = false) {
    FIXED("Status: Fixed", 1),
    PENDING_FIX("Status: Pending Fix", 2),
    ALL_CLEAR("Status: All Clear", 3),
    CRITICAL_NOT_FIXED("Status: Critical - Not Fixed", 4, requiresPulse = true)
}

data class DiagnosticLog(
    val id: String,
    val icon: ImageVector,
    val colorId: Int, // 1: Primary, 2: Secondary, 3: SurfaceVariant, 4: Error
    val dateStr: String,
    val title: String,
    val status: LogStatus,
    val actionText: String? = null,
    val hasDescriptionIcon: Boolean = false,
    val isFaded: Boolean = false
)

val mockLogs = listOf(
    DiagnosticLog(
        id = "1", icon = Icons.Default.Biotech, colorId = 1,
        dateStr = "OCT 24, 2023 • 14:30 PM", title = "Brake Pad Wear Level 4",
        status = LogStatus.FIXED, hasDescriptionIcon = true
    ),
    DiagnosticLog(
        id = "2", icon = Icons.Default.Analytics, colorId = 2,
        dateStr = "OCT 12, 2023 • 09:15 AM", title = "Engine Misfire Detected",
        status = LogStatus.PENDING_FIX, actionText = "SCHEDULE SERVICE"
    ),
    DiagnosticLog(
        id = "3", icon = Icons.Default.MonitorHeart, colorId = 3,
        dateStr = "SEP 28, 2023 • 18:45 PM", title = "Routine System Scan",
        status = LogStatus.ALL_CLEAR
    ),
    DiagnosticLog(
        id = "4", icon = Icons.Default.Report, colorId = 4,
        dateStr = "SEP 15, 2023 • 11:20 AM", title = "Tire Pressure Sensor Failure",
        status = LogStatus.CRITICAL_NOT_FIXED, actionText = "URGENT ACTION"
    ),
    DiagnosticLog(
        id = "5", icon = Icons.Default.Warning, colorId = 1,
        dateStr = "AUG 02, 2023 • 16:00 PM", title = "Coolant Level Low",
        status = LogStatus.FIXED, isFaded = true
    )
)
