package com.carsense.ai.ui.diagnostic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DiagnosticLogCard(log: DiagnosticLog, primaryColor: Color, secondaryColor: Color) {
    val accentColor = getColorById(log.colorId, primaryColor, secondaryColor)
    val alphaBackground = if (log.isFaded) 0.2f else 0.4f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2A2A2A).copy(alpha = alphaBackground))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .clickable { }
    ) {
        // Left accent border
        Box(modifier = Modifier.matchParentSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    .background(accentColor)
                    .align(Alignment.CenterStart)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                LogIconBox(accentColor = accentColor, icon = log.icon)
                Spacer(modifier = Modifier.width(20.dp))
                LogTextDetails(
                    dateStr = log.dateStr,
                    title = log.title,
                    status = log.status,
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor
                )
            }

            LogActionRow(log = log, accentColor = accentColor)
        }
    }
}
