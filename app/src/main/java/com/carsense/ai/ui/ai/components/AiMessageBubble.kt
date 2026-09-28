package com.carsense.ai.ui.ai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*

enum class MessageType {
    USER, AI
}

@Composable
fun AiMessageBubble(
    text: String,
    type: MessageType,
    modifier: Modifier = Modifier,
    telemetryContent: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = if (type == MessageType.USER) Alignment.End else Alignment.Start
    ) {
        if (type == MessageType.AI) {
            AiHeader()
            Spacer(modifier = Modifier.height(10.dp))
        }

        Box(
            modifier = Modifier
                .widthIn(max = if (type == MessageType.USER) 300.dp else 340.dp)
                .clip(RoundedCornerShape(20.dp))
                .then(
                    if (type == MessageType.AI) {
                        Modifier
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        primaryContainerDark.copy(alpha = 0.1f),
                                        secondaryDark.copy(alpha = 0.1f)
                                    )
                                )
                            )
                            .background(Color(0xFF353534).copy(alpha = 0.4f))
                            .border(
                                width = 1.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        primaryContainerDark.copy(alpha = 0.3f),
                                        Color.Transparent,
                                        secondaryDark.copy(alpha = 0.3f)
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                    } else {
                        Modifier.background(surfaceContainerHigh)
                    }
                )
                .padding(10.dp)
        ) {
            Column {
                if (type == MessageType.AI) {
                    AiMessageText(text)
                    if (telemetryContent != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        TelemetryBox(telemetryContent)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Would you like me to run a full diagnostic scan?",
                            color = onBackgroundDark,
                            fontSize = 16.sp,

                        )
                    }
                } else {
                    Text(
                        text = text,
                        color = onSurfaceDark,
                        fontSize = 14.sp,

                    )
                }
            }
        }

        if (type == MessageType.USER) {
            Text(
                text = "USER",
                color = onSurfaceVariantDark,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp, end = 8.dp)
            )
        }
    }
}

@Composable
private fun AiHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(primaryContainerDark, secondaryDark)
                    ),
                    CircleShape
                )
                .shadow(elevation = 15.dp, shape = CircleShape, ambientColor = primaryContainerDark, spotColor = primaryContainerDark),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = "AI SPECIALIST",
            color = primaryContainerDark,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
    }
}

@Composable
private fun AiMessageText(text: String) {
    // Basic rich text implementation for the demo
    val annotatedString = buildAnnotatedString {
        val parts = text.split("clicking sound")
        if (parts.size > 1) {
            append(parts[parts.size - 2])
            withStyle(style = SpanStyle(color = primaryContainerDark, fontWeight = FontWeight.Medium)) {
                append("clicking sound")
            }
            append(parts[parts.size - 1])
        } else {
            append(text)
        }
    }

    Text(
        text = annotatedString,
        color = onBackgroundDark,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
    )
}

@Composable
private fun TelemetryBox(content: String) {
    val annotatedContent = buildAnnotatedString {
        val parts = content.split("slight vibration")
        if (parts.size > 1) {
            append(parts[parts.size - 2])
            withStyle(style = SpanStyle(color = secondaryDark, fontWeight = FontWeight.Medium)) {
                append("slight vibration")
            }
            append(parts[parts.size - 1])
        } else {
            append(content)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = annotatedContent,
            color = onSurfaceVariantDark,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}
