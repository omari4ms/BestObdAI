package com.carsense.ai.ui.star.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*
@Preview
@Composable
fun CreateAccountScreen(onContinueWithEmail: () -> Unit = {}, onSkip: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313)) // surface
    ) {
        // Background Gradient Overlays
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .drawBehind {
                    drawCircle(
                        color = secondaryContainerDark.copy(alpha = 0.1f),
                        radius = size.width * 1.5f,
                        center = Offset(0f, 0f)
                    )
                }
                .blur(100.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Logo & Stepper
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
                }
            }

            // Middle Section: Hero Text & Visual
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(primaryContainerDark.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "NEURAL SYNC",
                        color = primaryContainerDark,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Text(
                    text = "UNLEASH THE",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "INTELLIGENCE",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(primaryContainerDark, secondaryDark)
                        )
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Join the elite network of performance-\ndriven pilots.",
                    color = onSurfaceVariantDark,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Bottom Section: Actions
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Continue with Email
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(CircleShape)
                        .background(primaryContainerDark)
                        .clickable { onContinueWithEmail() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CONTINUE WITH EMAIL",
                        color = Color(0xFF003737),
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Social Logins
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SocialButton(text = "G", modifier = Modifier.weight(1f))
                    SocialButton(text = "A", modifier = Modifier.weight(1f))
                    SocialButton(text = "F", modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Footer Link
                Text(
                    text = "SKIP FOR NOW",
                    color = onSurfaceVariantDark,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .clickable { onSkip() }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun SocialButton(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF353534).copy(alpha = 0.4f)) // glass-panel
            .border(1.dp, outlineVariantDark.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
       Text(text = text, color = Color.White, style = MaterialTheme.typography.titleLarge)

    }
}
