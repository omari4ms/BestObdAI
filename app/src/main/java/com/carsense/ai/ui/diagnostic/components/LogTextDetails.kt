package com.carsense.ai.ui.diagnostic.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.carsense.ai.ui.theme.onSurfaceVariantDark

@Composable
fun LogTextDetails(
    dateStr: String,
    title: String,
    status: LogStatus,
    primaryColor: Color,
    secondaryColor: Color
) {
    Column {
        Text(
            text = dateStr,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = onSurfaceVariantDark,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        LogStatusRow(
            status = status,
            primaryColor = primaryColor,
            secondaryColor = secondaryColor
        )
    }
}
