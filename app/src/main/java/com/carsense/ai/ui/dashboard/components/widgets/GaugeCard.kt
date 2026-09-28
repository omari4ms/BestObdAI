package com.carsense.ai.ui.dashboard.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.onSurfaceVariantDark

@Composable
fun GaugeCard(
        modifier: Modifier = Modifier,
        percentage: Float,
        mainText: String,
        title: String,
        subtitle: String,
        subtitleColor: Color,
        gradientColors: List<Color>
) {
    Box(
            modifier =
                    modifier.clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFF2A2A2A).copy(alpha = 0.4f))
                            .border(
                                    1.dp,
                                    Color.White.copy(alpha = 0.05f),
                                    RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp)
    ) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Circular Gauge Box
            Box(
                    modifier =
                            Modifier.fillMaxWidth(0.8f).aspectRatio(1f).drawBehind {
                                val strokeWidth = 8.dp.toPx()

                                // Background Track
                                drawArc(
                                        color = Color(0xFF353534),
                                        startAngle = -90f,
                                        sweepAngle = 360f,
                                        useCenter = false,
                                        style = Stroke(strokeWidth)
                                )

                                // Progress
                                drawArc(
                                        brush = Brush.linearGradient(gradientColors),
                                        startAngle = -90f,
                                        sweepAngle = 360f * percentage,
                                        useCenter = false,
                                        style = Stroke(strokeWidth, cap = StrokeCap.Round)
                                )
                            },
                    contentAlignment = Alignment.Center
            ) {
                Text(
                        text = mainText,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                    text = title,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceVariantDark,
                    letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, fontSize = 12.sp, color = subtitleColor)
        }
    }
}
