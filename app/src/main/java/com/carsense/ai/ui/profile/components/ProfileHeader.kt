package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*

@Composable
fun ProfileHeader() {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
                text = "System Configuration",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Fine-tune your ethereal co-pilot's performance and interface.",
            fontSize = 10.sp,
            color = onSurfaceVariantDark
        )
    }
}
