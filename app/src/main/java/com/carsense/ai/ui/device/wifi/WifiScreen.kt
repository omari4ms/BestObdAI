package com.carsense.ai.ui.device.wifi

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiFind
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carsense.ai.device.connection.wifi.WifiConnectionState
import com.carsense.ai.device.connection.wifi.WifiNetworkDomain
import com.carsense.ai.ui.profile.components.BentoCard
import com.carsense.ai.viewmodels.WifiViewModel

@Preview
@Composable
fun WifiScreen(
    viewModel: WifiViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var isScanning by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(16.dp)
    ) {
        BentoCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Header
            Column(modifier = Modifier.padding(bottom = 20.dp)) {
                Text(
                    text = "INTELLIGENCE OS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "WI-FI PROTOCOL",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )
            }

            // Scan Button (Glowing Cyan Pill)
            ScanButton(
                isScanning = isScanning,
                onStartScan = {
                    isScanning = true
                    viewModel.startScan()
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Section Title & Status
            val isConnected = uiState.connectionState is WifiConnectionState.Connected
            val statusText = when (uiState.connectionState) {
                is WifiConnectionState.Connected -> "Connected"
                is WifiConnectionState.Connecting -> "Connecting..."
                is WifiConnectionState.Disconnected -> "Disconnected"
                is WifiConnectionState.Error -> "Connection Error"
            }
            val statusColor = if (isConnected) Color(0xFF00FBFB) else Color.White.copy(alpha = 0.6f)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Wifi,
                        contentDescription = null,
                        tint = Color(0xFF00FBFB),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Available Networks",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Text(
                    text = statusText,
                    style = MaterialTheme.typography.labelSmall,
                    color = statusColor,
                    fontWeight = FontWeight.Medium
                )
            }

            // Network List
            NetworkListSection(
                networks = uiState.scannedNetworks,
                onNetworkClick = { network ->
                    viewModel.connectToNetwork(network.ssid, null)
                }
            )
        }
    }
}

@Composable
private fun ScanButton(
    isScanning: Boolean,
    onStartScan: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "scan_transition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scan_rotation"
    )

    Button(
        onClick = onStartScan,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(
                elevation = 12.dp,
                shape = CircleShape,
                spotColor = Color(0xFF00FBFB),
                ambientColor = Color(0xFF00FBFB)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00FBFB),
            contentColor = Color(0xFF003737)
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        AnimatedContent(targetState = isScanning, label = "scan_button_content") { scanning ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (scanning) {
                    Icon(
                        imageVector = Icons.Default.WifiFind,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(rotation)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Scanning...",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.WifiFind,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Scan for Networks",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun NetworkListSection(
    networks: List<WifiNetworkDomain>,
    onNetworkClick: (WifiNetworkDomain) -> Unit
) {
    if (networks.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.03f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Wi-Fi networks found yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(networks) { network ->
                NetworkItem(
                    ssid = network.ssid.ifEmpty { "Hidden Network" },
                    signal = "Signal Strength: Good",
                    icon = Icons.Default.Wifi,
                    iconTint = Color(0xFF00FBFB),
                    onClick = { onNetworkClick(network) }
                )
            }
        }
    }
}

@Composable
private fun NetworkItem(
    ssid: String,
    signal: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.05f))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
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
                    text = ssid,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Text(
                    text = signal,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Secured",
                tint = Color.White.copy(alpha = 0.4f),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Connect",
                tint = Color.White.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
