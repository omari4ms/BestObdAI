package com.carsense.ai.ui.star.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.outlineVariantDark
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.secondaryDark

@Preview
@Composable
fun WelcomeScreen(onGetStarted: () -> Unit = {}) {
    Box(
        modifier = Modifier.statusBarsPadding()
            .fillMaxSize()
            .background(Color(0xFF0e0e0e)) // surface-container-lowest
    ) {
        // Ambient background glow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        color = primaryContainerDark.copy(alpha = 0.05f),
                        radius = size.minDimension * 0.8f,
                        center = Offset(size.width * 0.2f, size.height * 0.3f)
                    )
                    drawCircle(
                        color = secondaryDark.copy(alpha = 0.05f),
                        radius = size.minDimension * 0.7f,
                        center = Offset(size.width * 0.8f, size.height * 0.7f)
                    )
                }
                .blur(80.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                    Box(modifier = Modifier.size(48.dp, 2.dp).background(outlineVariantDark))
                    Box(modifier = Modifier.size(48.dp, 2.dp).background(outlineVariantDark))
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            // Main Text content
            Text(
                text = "WELCOME TO\nNEBULA",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = Shadow(
                        color = primaryContainerDark.copy(alpha = 0.5f),
                        blurRadius = 16f
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your intelligent co-pilot for advanced\nvehicle diagnostics and real-time\ntelemetry.",
                color = onSurfaceVariantDark,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Language Selector Button
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(primaryContainerDark.copy(alpha = 0.1f))
                    .border(1.dp, primaryContainerDark.copy(alpha = 0.5f), CircleShape)
                    .clickable { /* Language Select logic here */ }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Public,
                    contentDescription = "Language",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "EN",
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Get Started Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(CircleShape)
                    .background(primaryContainerDark)
                    .clickable { onGetStarted() }
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "GET STARTED",
                        color = Color(0xFF003737), // on-primary-container
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Get Started",
                        tint = Color(0xFF003737),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            
            // Bottom System Status Text
            Text(
                text = "CRYPTO_SECURE_ENCRYPTION_ACTIVE",
                color = onSurfaceVariantDark.copy(alpha = 0.4f),
                style = MaterialTheme.typography.labelSmall
            )
            
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

