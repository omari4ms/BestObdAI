package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*
@Composable
fun ColorPickerBottomSheet(
    initialColor: Color,
    onColorApplied: (Color) -> Unit
) {
    var currentColor by remember { mutableStateOf(initialColor) }
    var hue by remember { mutableFloatStateOf(0f) }
    var saturation by remember { mutableFloatStateOf(1f) }
    var opacity by remember { mutableFloatStateOf(currentColor.alpha) }

    // Initialize HSV from initial color
    LaunchedEffect(initialColor) {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(
            android.graphics.Color.argb(
                (initialColor.alpha * 255).toInt(),
                (initialColor.red * 255).toInt(),
                (initialColor.green * 255).toInt(),
                (initialColor.blue * 255).toInt()
            ),
            hsv
        )
        hue = hsv[0]
        saturation = hsv[1]
    }

    // Update currentColor when HSV or opacity changes
    LaunchedEffect(hue, saturation, opacity) {
        val hsvColor = android.graphics.Color.HSVToColor((opacity * 255).toInt(), floatArrayOf(hue, saturation, 1f))
        currentColor = Color(hsvColor)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Color Wheel
        Box(
            modifier = Modifier
                .size(240.dp)
                .shadow(elevation = 40.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(Color(0xFF1A1A1A))
                .pointerInput(Unit) {
                    fun updateColor(offset: Offset) {
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val dx = offset.x - center.x
                        val dy = offset.y - center.y
                        val distance = sqrt(dx * dx + dy * dy)
                        val radius = size.width / 2f
                        
                        if (distance <= radius) {
                            val angle = atan2(dy, dx)
                            hue = (angle * 180f / PI.toFloat() + 360f) % 360f
                            saturation = (distance / radius).coerceIn(0f, 1f)
                        }
                    }
                    detectTapGestures { updateColor(it) }
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val dx = change.position.x - center.x
                        val dy = change.position.y - center.y
                        val distance = sqrt(dx * dx + dy * dy)
                        val radius = size.width / 2f
                        
                        val angle = atan2(dy, dx)
                        hue = (angle * 180f / PI.toFloat() + 360f) % 360f
                        saturation = (distance / radius).coerceIn(0f, 1f)
                        change.consume()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val colors = listOf(
                    Color.Red, Color.Yellow, Color.Green,
                    Color.Cyan, Color.Blue, Color.Magenta, Color.Red
                )
                drawCircle(
                    brush = Brush.sweepGradient(colors),
                    radius = size.minDimension / 2
                )
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.White, Color.Transparent),
                        radius = size.minDimension / 2
                    ),
                    alpha = 0.5f
                )
            }
            
            // Picker Reticle
            val radius = 120.dp
            val angleRad = hue * PI.toFloat() / 180f
            val offsetX = (saturation * radius.value * cos(angleRad)).dp
            val offsetY = (saturation * radius.value * sin(angleRad)).dp

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .size(24.dp)
                    .border(2.dp, Color.White, CircleShape)
                    .shadow(8.dp, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(6.dp).background(Color.White, CircleShape))
            }
        }

        // Sliders
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // Hue Slider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("HUE", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.6f), letterSpacing = 1.sp)
                    Text("${hue.toInt()}°", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
                Slider(
                    value = hue,
                    onValueChange = { hue = it },
                    valueRange = 0f..360f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.Transparent,
                        inactiveTrackColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red)
                            )
                        )
                )
            }

            // Opacity Slider
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("OPACITY", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(0.6f), letterSpacing = 1.sp)
                    Text("${(opacity * 100).toInt()}%", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
                Slider(
                    value = opacity,
                    onValueChange = { opacity = it },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = currentColor.copy(alpha = 0.8f),
                        inactiveTrackColor = Color.White.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier.height(10.dp)
                )
            }
        }

        // Hex Input & Swatch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .shadow(20.dp, CircleShape, spotColor = currentColor)
                    .background(currentColor, CircleShape)
                    .border(2.dp, Color.White.copy(alpha = 0.1f), CircleShape)
            )
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "#",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(0.4f),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = String.format("%06X", (0xFFFFFF and currentColor.value.toLong().toInt())).uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            IconButton(
                onClick = { /* Copy hex */ },
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color.White, modifier = Modifier.size(20.dp))
            }
        }

        // Apply Button
        Button(
            onClick = { onColorApplied(currentColor) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(20.dp, RoundedCornerShape(24.dp), spotColor = currentColor),
            colors = ButtonDefaults.buttonColors(
                containerColor = currentColor,
                contentColor = if (currentColor.luminance() > 0.5f) Color.Black else Color.White
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                "APPLY COLOR",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }
    }
}
