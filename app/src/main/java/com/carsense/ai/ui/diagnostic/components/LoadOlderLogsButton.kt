package com.carsense.ai.ui.diagnostic.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.carsense.ai.ui.theme.onSurfaceVariantDark

@Composable
fun LoadOlderLogsButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.width(48.dp), color = Color.White.copy(alpha = 0.1f))
        Text(
            text = "LOAD OLDER LOGS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = onSurfaceVariantDark,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(modifier = Modifier.width(48.dp), color = Color.White.copy(alpha = 0.1f))
    }
}
