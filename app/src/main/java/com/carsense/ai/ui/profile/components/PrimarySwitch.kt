package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.surfaceContainerHigh

@Composable
fun PrimarySwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Black, // Darker thumb on bright track
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = onSurfaceVariantDark,
            uncheckedTrackColor = surfaceContainerHigh,
            uncheckedBorderColor = Color.Transparent
        )
    )
}
