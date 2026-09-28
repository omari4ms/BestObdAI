package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocalizationCard(
    selectedLanguage: String = "English (United States)",
    selectedTimeZone: String = "PST (UTC -8:00)",
    onLanguageClick: () -> Unit = {},
    onTimeZoneClick: () -> Unit = {}
) {
    BentoCard {
        Text(
            text = "Localization",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            LocalizationItem(
                icon = Icons.Default.Language, 
                label = "Display Language", 
                value = selectedLanguage,
                onClick = onLanguageClick
            )
            LocalizationItem(
                icon = Icons.Default.Schedule, 
                label = "Time Zone", 
                value = selectedTimeZone,
                onClick = onTimeZoneClick
            )
        }
    }
}
