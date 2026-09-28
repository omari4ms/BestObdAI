package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*
@Preview
@Composable
fun InterfaceStyleCard(
    isDarkMode: Boolean = true,
    isGlassmorphismEnabled: Boolean = true,
    onDarkModeToggle: () -> Unit = {},
    onGlassmorphismToggle: () -> Unit = {}
) {
    BentoCard {
        Text(
            text = "Interface Style".uppercase(),
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Dark Mode
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Dark Mode", color = Color.White, fontSize = 14.sp)
                IconButton(
                    onClick = onDarkModeToggle,
                    modifier = Modifier
                        .background(
                            if (isDarkMode) primaryContainerDark.copy(alpha = 0.2f) 
                            else surfaceContainerHigh, 
                            CircleShape
                        )
                        .border(
                            1.dp, 
                            if (isDarkMode) primaryContainerDark.copy(alpha = 0.4f) 
                            else Color.White.copy(alpha = 0.1f), 
                            CircleShape
                        )
                ) {
                    Icon(
                        if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode, 
                        null, 
                        tint = if (isDarkMode) primaryContainerDark else onSurfaceVariantDark
                    )
                }
            }
            // Glassmorphism
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Glassmorphism", color = Color.White, fontSize = 14.sp)
                IconButton(
                    onClick = onGlassmorphismToggle,
                    modifier = Modifier
                        .background(
                            if (isGlassmorphismEnabled) primaryContainerDark.copy(alpha = 0.2f) 
                            else surfaceContainerHigh, 
                            CircleShape
                        )
                        .border(
                            1.dp, 
                            if (isGlassmorphismEnabled) primaryContainerDark.copy(alpha = 0.4f) 
                            else Color.White.copy(alpha = 0.1f), 
                            CircleShape
                        )
                ) {
                    Icon(
                        Icons.Default.BlurOn, 
                        null, 
                        tint = if (isGlassmorphismEnabled) primaryContainerDark else onSurfaceVariantDark
                    )
                }
            }
        }
    }
}
