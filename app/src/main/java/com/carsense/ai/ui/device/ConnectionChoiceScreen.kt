package com.carsense.ai.ui.device

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.carsense.ai.ui.device.bluetooth.BluetoothScreen
import com.carsense.ai.ui.device.wifi.WifiScreen
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.outlineVariantDark
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.secondaryDark

enum class LinkState { Default, Bluetooth, Wifi }

@Preview
@Composable
fun ConnectionChose(
    onBack: () -> Unit = {}
) {
    var linkState by remember { mutableStateOf(LinkState.Default) }

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
            BackHandler { onBack() }
            Box(
                modifier = Modifier.verticalScroll(ScrollState(0))

                    .background(Color(0xFF131313))
            ) {
                // Atmospheric Background



                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(16.dp))

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


