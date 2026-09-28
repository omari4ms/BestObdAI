package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.errorDark
import com.carsense.ai.ui.theme.surfaceContainerLowest

@Composable
fun StatusBadge(text: String, isConnected: Boolean) {
    Row(
        modifier = Modifier
            .background(surfaceContainerLowest, CircleShape)
            .border(1.dp, Color.White.copy(alpha = 0.05f), CircleShape)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(if (isConnected) MaterialTheme.colorScheme.primaryContainer else errorDark)
        )
        Text(
            text = text.uppercase(),
            color = if (isConnected) MaterialTheme.colorScheme.primaryContainer else errorDark,
            fontSize = 7.sp,
            fontWeight = FontWeight.Black,

        )
    }
}
