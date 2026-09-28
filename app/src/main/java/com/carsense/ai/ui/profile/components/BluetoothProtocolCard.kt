package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun BluetoothProtocolCard() {
    BentoCard {
        // Header
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            Text(
                text = "INTELLIGENCE OS",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 2.sp
            )
            Text(
                text = "BLUETOOTH PROTOCOL",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 2.sp
            )
        }

        // Add New Device Button
        Button(
            onClick = { /* TODO: Implement device discovery */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = CircleShape,
                    spotColor = Color(0xFF00fbfb),
                    ambientColor = Color(0xFF00fbfb)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00fbfb),
                contentColor = Color(0xFF003737)
            ),
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add New Device",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Paired Devices Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.BluetoothConnected,
                contentDescription = null,
                tint = Color(0xFF00fbfb),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Paired Devices",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        // Device List
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            DeviceItem(
                name = "Nebula Pods Pro",
                status = "Connected • 82% Battery",
                statusColor = Color(0xFF00fbfb).copy(alpha = 0.8f),
                icon = Icons.Default.Bluetooth,
                iconTint = Color(0xFF00fbfb)
            )
            DeviceItem(
                name = "Core Watch Series X",
                status = "Paired",
                statusColor = Color.White.copy(alpha = 0.6f),
                icon = Icons.Default.Watch,
                iconTint = Color.White.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun DeviceItem(
    name: String,
    status: String,
    statusColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.05f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.05f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.labelSmall,
                    color = statusColor
                )
            }
        }
        
        IconButton(
            onClick = { /* TODO: Handle edit */ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
