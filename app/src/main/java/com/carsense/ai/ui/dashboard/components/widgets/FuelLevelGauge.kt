package com.carsense.ai.ui.dashboard.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*
@Preview
@Composable
fun FuelLevelGauge(
    fuelPercentage: Int = 50,
    estimatedRange: String = "EST. 342 KM"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(surfaceContainerHigh)

            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ENERGY RESERVE",
                color = onSurfaceVariantDark,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Icon(
                imageVector = Icons.Default.EvStation,
                contentDescription = "Energy Reserve",
                tint = primaryContainerDark
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Level Bars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            val weights = listOf(1f, 0.85f, 0.70f, 0.55f, 0.40f, 0.40f, 0.40f, 0.40f)
            
            // Calculate which bars are active based on fuelPercentage
            val barCount = 8
            val activeBars = (fuelPercentage / 100f * barCount).toInt()
            
            for (i in 0 until barCount) {
                // The bars are drawn from left to right, representing lower to higher fuel levels.
                // Reversing the index comparison since the left-most bar is the lowest fuel level.
                // Actually, wait, HTML design has high height on left, low height on right, 
                // Wait: left is tall? Original alphas:
                // alphas = listOf(0.2f, 0.2f, 0.4f, 0.6f, 1f, 0f, 0f, 0f)
                // Meaning the first 5 bars left-to-right were lit up. 
                // So left-to-right is 0% to 100%. Lit bars are from 0 index up to activeBars.
                val isActive = i < activeBars
                val isTip = i == activeBars - 1
                val barAlpha = if (isActive) {
                    if (isTip) 1f else 0.4f + (0.2f * (i.toFloat() / barCount))
                } else 0f
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(weights[i])
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            if (barAlpha > 0f) primaryContainerDark.copy(alpha = barAlpha)
                            else surfaceContainerHighest
                        )
                        .then(
                            if (isTip) Modifier.shadow(
                                elevation = 15.dp, 
                                spotColor = primaryContainerDark,
                                ambientColor = primaryContainerDark
                            ) else Modifier
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${fuelPercentage}%",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = estimatedRange,
                color = onSurfaceVariantDark,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
