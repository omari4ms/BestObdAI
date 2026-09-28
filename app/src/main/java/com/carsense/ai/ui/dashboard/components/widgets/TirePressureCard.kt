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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.errorContainerDark
import com.carsense.ai.ui.theme.errorDark
import com.carsense.ai.ui.theme.onErrorContainerDark
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.warningColor

@Composable
fun TirePressureCard(
    flPressure: String = "2.2 BAR",
    flWarning: Boolean = false,
    frPressure: String = "1.9 BAR",
    frWarning: Boolean = true,
    rlPressure: String = "2.2 BAR",
    rlWarning: Boolean = false,
    rrPressure: String = "2.2 BAR",
    rrWarning: Boolean = false,
    warningMessage: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF201F1F).copy(alpha = 0.4f)) // Glass panel approximation
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TIRE PRESSURE MONITORING",
            color = onSurfaceVariantDark,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentAlignment = Alignment.Center
        ) {
            // Car Chassis Schematic
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.05f))
                    .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
            ) {
                // Axles
                Box(modifier = Modifier.fillMaxWidth().height(4.dp).align(Alignment.TopCenter).offset(y = 40.dp).background(Color.White.copy(alpha=0.1f), CircleShape))
                Box(modifier = Modifier.fillMaxWidth().height(4.dp).align(Alignment.BottomCenter).offset(y = (-40).dp).background(Color.White.copy(alpha=0.1f), CircleShape))
                
                // Center Pillar
                Box(modifier = Modifier.width(6.dp).fillMaxHeight(0.6f).background(Color.White.copy(alpha=0.1f), CircleShape).align(Alignment.Center))

                // Chassis Centers
                Box(modifier = Modifier.size(32.dp).align(Alignment.TopCenter).offset(y = 26.dp).background(Color(0xFF201F1F), CircleShape).border(1.dp, Color.White.copy(alpha=0.1f), CircleShape), contentAlignment=Alignment.Center) {
                    Box(modifier=Modifier.size(12.dp).background(Color.White.copy(alpha=0.2f), CircleShape))
                }
                Box(modifier = Modifier.size(32.dp).align(Alignment.BottomCenter).offset(y = (-26).dp).background(Color(0xFF201F1F), CircleShape).border(1.dp, Color.White.copy(alpha=0.1f), CircleShape), contentAlignment=Alignment.Center) {
                    Box(modifier=Modifier.size(12.dp).background(Color.White.copy(alpha=0.2f), CircleShape))
                }
            }

            // Tires
            // Front Left
            TireWidget(modifier = Modifier.align(Alignment.TopCenter).offset(x = (-60).dp, y = 40.dp), color = if(flWarning) warningColor else primaryContainerDark.copy(alpha=0.6f))
            // Front Right
            TireWidget(modifier = Modifier.align(Alignment.TopCenter).offset(x = 60.dp, y = 40.dp), color = if(frWarning) warningColor else primaryContainerDark.copy(alpha=0.6f))
            // Rear Left
            TireWidget(modifier = Modifier.align(Alignment.BottomCenter).offset(x = (-60).dp, y = (-40).dp), color = if(rlWarning) warningColor else primaryContainerDark.copy(alpha=0.6f))
            // Rear Right
            TireWidget(modifier = Modifier.align(Alignment.BottomCenter).offset(x = 60.dp, y = (-40).dp), color = if(rrWarning) warningColor else primaryContainerDark.copy(alpha=0.6f))

            // Text Callouts
            Callout(modifier = Modifier.align(Alignment.TopStart).offset(x = 10.dp, y = 10.dp), pressure = flPressure, color = if(flWarning) warningColor else Color.White)
            Callout(modifier = Modifier.align(Alignment.TopEnd).offset(x = (-10).dp, y = 10.dp), pressure = frPressure, color = if(frWarning) warningColor else Color.White)
            Callout(modifier = Modifier.align(Alignment.BottomStart).offset(x = 10.dp, y = (-60).dp), pressure = rlPressure, color = if(rlWarning) warningColor else Color.White)
            Callout(modifier = Modifier.align(Alignment.BottomEnd).offset(x = (-10).dp, y = (-60).dp), pressure = rrPressure, color = if(rrWarning) warningColor else Color.White)
        }


        Spacer(modifier = Modifier.height(20.dp))
        // Warning Message
        if (warningMessage != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(CircleShape)
                    .background(errorContainerDark.copy(alpha = 0.2f))
                    .border(1.dp, errorDark.copy(alpha = 0.3f), CircleShape)
                    .padding(vertical = 12.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                    tint = errorDark,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = warningMessage,
                    color = onErrorContainerDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp
                )
            }
        }
    }
}

@Composable
fun TireWidget(modifier: Modifier, color: Color) {
    Box(
        modifier = modifier
            .width(20.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color)
            .shadow(
                elevation = if(color == warningColor) 15.dp else 10.dp,
                spotColor = color,
                ambientColor = color
            )
    )
}

@Composable
fun Callout(modifier: Modifier, pressure: String, color: Color) {
    Column(modifier = modifier) {
        Text(
            text = pressure,
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        // A simple line for the callout design
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(1.dp)
                .background(color.copy(alpha = 0.4f))
        )
    }
}
