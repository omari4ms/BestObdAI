package com.carsense.ai.ui.dashboard.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.surfaceContainerHighest
@Preview
@Composable
fun ConsumptionChartCard(
    title: String = "Fuel Consumption",
    timeRange: String = "Last 7 Days",
    primaryColor: Color = MaterialTheme.colorScheme.primaryContainer,
    secondaryColor: Color = MaterialTheme.colorScheme.secondary
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF2A2A2A).copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(surfaceContainerHighest)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = timeRange, fontSize = 12.sp, color = onSurfaceVariantDark)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bar Chart
            val bars = listOf(0.4f, 0.6f, 0.8f, 0.5f, 0.7f, 0.45f, 0.9f)
            val barColors = listOf(
                surfaceContainerHighest,
                primaryColor.copy(alpha = 0.4f),
                primaryColor.copy(alpha = 0.6f),
                primaryColor,
                primaryColor.copy(alpha = 0.4f),
                primaryColor.copy(alpha = 0.2f),
                secondaryColor.copy(alpha = 0.4f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                bars.forEachIndexed { index, fillFrac ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(fillFrac)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(barColors[index])
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MON",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceVariantDark,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "SUN",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceVariantDark,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}
