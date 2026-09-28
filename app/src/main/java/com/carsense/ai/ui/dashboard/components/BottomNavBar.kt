package com.carsense.ai.ui.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.Screen

@Composable
fun BottomNavBar(selectedScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    Row(
            modifier =
                    Modifier.fillMaxWidth()

                            .background(Color.Black.copy(alpha = 0.9f))
                            .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        NavItem(
                icon = Icons.Default.Speed,
                label = "Dashboard",
                isActive = selectedScreen == Screen.Dashboard,
                onClick = { onScreenSelected(Screen.Dashboard) }
        )
        NavItem(
                icon = Icons.Default.Analytics,
                label = "Diagnostic",
                isActive = selectedScreen == Screen.Diagnostic,
                onClick = { onScreenSelected(Screen.Diagnostic) }
        )
        NavItem(
                icon = Icons.Default.AutoAwesome,
                label = "AI",
                isActive = selectedScreen == Screen.Ai,
                onClick = { onScreenSelected(Screen.Ai) }
        )
        NavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isActive = selectedScreen == Screen.Profile,
                onClick = { onScreenSelected(Screen.Profile) }
        )
    }
}

@Composable
fun NavItem(icon: ImageVector, label: String, isActive: Boolean, onClick: () -> Unit) {
    val contentColor =
            if (isActive) MaterialTheme.colorScheme.primaryContainer
            else Color.White.copy(alpha = 0.4f)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = contentColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}
