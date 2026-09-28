package com.carsense.ai.ui.ai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.OilBarrel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.onSurfaceDark
import com.carsense.ai.ui.theme.primaryContainerDark

data class QuickAction(
    val label: String,
    val icon: ImageVector,
    val isPrimary: Boolean = false
)
@Preview
@Composable
fun QuickActionChips(
    modifier: Modifier = Modifier
) {
    val actions = listOf(
        QuickAction("Run diagnostic", Icons.Default.Analytics, isPrimary = true),
        QuickAction("Check oil level", Icons.Default.OilBarrel),
        QuickAction("Find a mechanic", Icons.Default.CarRepair)
    )

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(actions) { action ->
            QuickActionChip(action)
        }
    }
}

@Composable
fun QuickActionChip(action: QuickAction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(CircleShape)
            .border(
                1.dp,
                if (action.isPrimary) primaryContainerDark.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                CircleShape
            )
            .then(
                if (action.isPrimary) Modifier.background(primaryContainerDark.copy(alpha = 0.05f)) else Modifier
            )
            .clickable { /* TODO */ }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = null,
            tint = if (action.isPrimary) primaryContainerDark else onSurfaceDark.copy(alpha = 0.7f),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = action.label,
            color = if (action.isPrimary) primaryContainerDark else onSurfaceDark,
            fontSize = 12.sp
        )
    }
}
