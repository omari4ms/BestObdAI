package com.carsense.ai.ui.star.components

import androidx.activity.compose.BackHandler
import com.carsense.ai.ui.device.bluetooth.BluetoothScreen
import com.carsense.ai.ui.device.wifi.WifiScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.outlineVariantDark
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.secondaryDark

enum class LinkState { Default, Bluetooth, Wifi }

@Preview
@Composable
fun LinkDeviceScreen(onFinish: () -> Unit = {}) {
    var linkState by remember { mutableStateOf(LinkState.Default) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    when (linkState) {
        LinkState.Bluetooth -> {
            BackHandler { linkState = LinkState.Default }
            BluetoothScreen()
        }
        LinkState.Wifi -> {
            BackHandler { linkState = LinkState.Default }
            WifiScreen()
        }
        LinkState.Default -> {
            Box(
        modifier = Modifier.statusBarsPadding().verticalScroll(ScrollState(0))

            .background(Color(0xFF131313))
    ) {
        // Atmospheric Background
        Box(
            modifier = Modifier

                .drawBehind {
                    drawCircle(
                        color = primaryContainerDark.copy(alpha = 0.08f),
                        radius = size.width * 0.8f,
                        center = Offset(size.width / 2, size.height * 0.3f)
                    )
                }
                .blur(80.dp)
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Placeholder for Sensors icon
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(primaryContainerDark, RoundedCornerShape(6.dp))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "AETHERIS AI",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Stepper
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.size(48.dp, 2.dp).background(outlineVariantDark))
                    Box(modifier = Modifier.size(48.dp, 2.dp).background(outlineVariantDark))
                    Box(
                        modifier = Modifier
                            .size(48.dp, 2.dp)
                            .background(primaryContainerDark) // active
                            .drawBehind {
                                drawRect(
                                    color = primaryContainerDark.copy(alpha = 0.5f),
                                    size = size.copy(height = size.height * 5),
                                    topLeft = Offset(0f, -size.height * 2)
                                )
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Central Interface Graphic
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Animated Orbits (Simulated)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, primaryContainerDark.copy(alpha = 0.1f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .border(1.dp, primaryContainerDark.copy(alpha = 0.2f), CircleShape)
                )

                // Central Pulsating Module
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF353534).copy(alpha = 0.4f)) // glass-card
                        .border(1.dp, primaryContainerDark.copy(alpha = 0.2f), CircleShape)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Memory,
                            contentDescription = null,
                            tint = primaryContainerDark,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Bluetooth, null, tint = secondaryDark, modifier = Modifier.size(16.dp))
                            Icon(Icons.Default.Wifi, null, tint = secondaryDark, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "OBD-II ACTIVE",
                            color = onSurfaceVariantDark,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                // Floating Data Points
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 10.dp, y = 20.dp)
                        .background(Color(0xFF353534).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .border(1.dp, outlineVariantDark.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("SYS_LINK: READY", color = primaryContainerDark, style = MaterialTheme.typography.labelSmall)
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = (-10).dp, y = (-20).dp)
                        .background(Color(0xFF353534).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .border(1.dp, outlineVariantDark.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("SIGNAL: 98%", color = secondaryDark, style = MaterialTheme.typography.labelSmall)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Headline & Copy
            Text(
                text = "ESTABLISH ",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "NEURAL LINK",
                color = primaryContainerDark,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sync your vehicle via Bluetooth or Wi-Fi to unlock real-time AI telematics. Aetheris requires a stable connection to monitor engine health and driving dynamics.",
                color = onSurfaceVariantDark,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Connection Cards
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Bluetooth Card
                ConnectionCard(
                    title = "Link Bluetooth",
                    description = "Fast, low-energy pairing for immediate dashboard metrics and voice assistant integration.",
                    icon = Icons.Default.Bluetooth,
                    iconTint = primaryContainerDark,
                    onClick = { linkState = LinkState.Bluetooth }
                )

                // Wi-Fi Card
                ConnectionCard(
                    title = "Connect Wi-Fi",
                    description = "High-bandwidth connection for deep neural analysis, firmware updates, and cloud syncing.",
                    icon = Icons.Default.Wifi,
                    iconTint = secondaryDark,
                    onClick = { linkState = LinkState.Wifi }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Footer Link
            Text(
                text = "SKIP FOR NOW",
                color = onSurfaceVariantDark,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .clickable { onFinish() }
                    .padding(8.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
        }
    }
}

@Composable
fun ConnectionCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF353534).copy(alpha = 0.4f)) // glass-card
            .border(1.dp, outlineVariantDark.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconTint.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = iconTint)
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = onSurfaceVariantDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                color = onSurfaceVariantDark,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


