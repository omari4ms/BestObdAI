package com.carsense.ai.ui.diagnostic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.surfaceContainerLowest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiagnosticSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    primaryColor: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text("Search diagnostics, parts...", color = onSurfaceVariantDark.copy(alpha = 0.5f))
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = onSurfaceVariantDark)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(surfaceContainerLowest),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = surfaceContainerLowest,
                unfocusedContainerColor = surfaceContainerLowest,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = primaryColor
            ),
            singleLine = true
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2A2A2A).copy(alpha = 0.4f))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                .clickable { }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Tune, contentDescription = "Filters", tint = primaryColor)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Filters", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
