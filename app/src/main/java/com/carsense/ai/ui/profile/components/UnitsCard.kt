package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.*

@Composable
fun UnitsCard(
    isMetric: Boolean = true,
    distanceUnit: String = "Kilometers (km)",
    pressureUnit: String = "Bar",
    onUnitToggle: () -> Unit = {}
) {
    BentoCard {
        Text(
            text = "Measurement Units",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(surfaceContainerLowest)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(if (isMetric) primaryContainerDark else Color.Transparent)
                    .clickable { if (!isMetric) onUnitToggle() }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "METRIC", 
                    color = if (isMetric) Color.Black else onSurfaceVariantDark, 
                    fontSize = 10.sp, 
                    fontWeight = FontWeight.Black, 
                    letterSpacing = 1.sp
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(if (!isMetric) primaryContainerDark else Color.Transparent)
                    .clickable { if (isMetric) onUnitToggle() }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "IMPERIAL", 
                    color = if (!isMetric) Color.Black else onSurfaceVariantDark, 
                    fontSize = 10.sp, 
                    fontWeight = FontWeight.Black, 
                    letterSpacing = 1.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Distance", color = onSurfaceVariantDark, fontSize = 14.sp)
            Text(distanceUnit, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Pressure", color = onSurfaceVariantDark, fontSize = 14.sp)
            Text(pressureUnit, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}
