package com.carsense.ai.ui.dashboard.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ConnectionType {
    NONE, BLUETOOTH, WIFI
}

data class ConnectionStatus(
    val isConnected: Boolean = false,
    val connectionType: ConnectionType = ConnectionType.NONE,
    val carModule: String? = null
)

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF000000)
@Composable
fun TopNavBar(
    showBackButtonOnly: Boolean = false,
    connectionStatus: ConnectionStatus = ConnectionStatus(isConnected = false),
    onBackClick: () -> Unit = {},
    onBluetoothConnectClick: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    Row(
        modifier = Modifier.statusBarsPadding()
            .fillMaxWidth()
            .background(
                Color.Black.copy(alpha = 0.9f)
            )
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = if (showBackButtonOnly) Arrangement.Start else Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButtonOnly) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
        } else {
            IconButton(onClick = {},){
                Icon(Icons.Default.Menu,"menu", tint = Color.Gray,modifier= Modifier.size(25.dp))
            }
            
            val isConn = connectionStatus.isConnected && connectionStatus.connectionType != ConnectionType.NONE
            val containerBg = if (isConn) Color.Green.copy(0.08f) else Color.Gray.copy(0.12f)
            val iconColor = if (isConn) Color.Green.copy(alpha) else Color.Gray
            val textColor = if (isConn) Color.Green else Color.Gray
            
            val iconVector = when {
                !isConn -> Icons.Default.Link
                connectionStatus.connectionType == ConnectionType.WIFI -> Icons.Default.Wifi
                else -> Icons.Default.BluetoothConnected
            }

            val displayText = when {
                !isConn -> "CONNECT"
                !connectionStatus.carModule.isNullOrEmpty() -> "MODEL : ${connectionStatus.carModule}"
                else -> "CONNECTED"
            }

            Row(
                modifier = Modifier
                    .clickable(onClick = onBluetoothConnectClick)
                    .background(containerBg, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = "Connection Status",
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    text = displayText,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }

            Row(
                modifier = Modifier
                    .clickable(onClick = {})
                    .background(Color.Yellow.copy(0.7f), shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.WorkspacePremium, "go pro", tint = Color.Black, modifier = Modifier.size(20.dp))
                Spacer(Modifier.size(6.dp))
                Text("GO PRO", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }

            IconButton(onClick = {},){
                Icon(Icons.Default.Notifications,"menu", tint = Color.Gray,modifier= Modifier.size(25.dp))
            }
        }
    }
}
