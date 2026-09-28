package com.carsense.ai.ui.diagnostic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.carsense.ai.ui.theme.surfaceContainerHighest

@Composable
fun LogActionRow(log: DiagnosticLog, accentColor: Color) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        if (log.actionText != null) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.2f))
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = log.actionText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor,
                    letterSpacing = 1.sp
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        if (log.hasDescriptionIcon) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(surfaceContainerHighest)
            ) {
                Icon(Icons.Default.Description, contentDescription = "Description", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
        Row(
            modifier =  if (!log.hasDescriptionIcon && log.actionText == null) Modifier.fillMaxWidth() else Modifier,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(surfaceContainerHighest)
            ) {
                Icon(Icons.Default.ChevronRight, contentDescription = "More", tint = Color.White)
            }
        }

    }
}
