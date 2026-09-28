package com.carsense.ai.ui.dashboard.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.carsense.ai.ui.theme.*
@Preview
@Composable
fun HeroSpeedGauge(
    speed: Int = 190,
    unit: String = "KM/H",
    maxSpeed: Int = 200,
    peakToday: String = "142",
    efficiency: String = "94%",
    stability: String = "Nominal",
    statusMessage: String = "OPTIMAL VELOCITY"
) {
    val progress = (speed.toFloat() / maxSpeed.toFloat()).coerceIn(0f, 1f)
    val sweepAngle = progress * 270f
    val primaryColor = MaterialTheme.colorScheme.primaryContainer
    val accentColor = MaterialTheme.colorScheme.primary
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(surfaceContainerLow)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(240.dp)
        ) {
            // Ambient glow behind gauge
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
            )

            // Gauge Ring Canvas
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 12.dp.toPx()
                val radius = size.minDimension / 2 - strokeWidth
                
                // Track
                drawArc(
                    color = surfaceContainerHighest,
                    startAngle = 135f,
                    sweepAngle = 270f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    size = Size(radius * 2, radius * 2),
                    topLeft = Offset(strokeWidth, strokeWidth)
                )

                // Progress gradient
                val gradient = Brush.linearGradient(
                    colors = listOf(primaryColor, secondaryDark),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height)
                )
                
                drawArc(
                    brush = gradient,
                    startAngle = 135f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    size = Size(radius * 2, radius * 2),
                    topLeft = Offset(strokeWidth, strokeWidth)
                )
            }

            // Center Text
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = speed.toString(),
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-2).sp
                )
                Text(
                    text = unit,
                    color = accentColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.White.copy(alpha = 0.1f), CircleShape)
                        .background(surfaceContainerHighest, CircleShape)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = statusMessage,
                        color = onSurfaceVariantDark,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeroStatItem("PEAK TODAY", peakToday, Color.White)
            Box(modifier = Modifier.width(1.dp).height(40.dp).background(outlineVariantDark.copy(alpha=0.5f)))
            HeroStatItem("EFFICIENCY", efficiency, secondaryDark)
            Box(modifier = Modifier.width(1.dp).height(40.dp).background(outlineVariantDark.copy(alpha=0.5f)))
            HeroStatItem("STABILITY", stability, primaryColor)
        }
    }
}

@Composable
fun HeroStatItem(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = onSurfaceVariantDark,
            fontSize = 10.sp,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = valueColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
