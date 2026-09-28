package com.carsense.ai.ui.diagnostic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.carsense.ai.ui.diagnostic.components.*
import com.carsense.ai.ui.theme.backgroundDark
import com.carsense.ai.ui.theme.onSurfaceVariantDark

@Composable
fun DiagnosticScreen() {
    val primaryColor = MaterialTheme.colorScheme.primaryContainer
    val secondaryColor = MaterialTheme.colorScheme.secondary

    var searchQuery by remember { mutableStateOf("") }
    val filters = listOf("All Logs", "Needs Attention", "Fixed", "Engine", "Brakes")
    var selectedFilter by remember { mutableStateOf(filters[0]) }

    Box(modifier = Modifier.fillMaxSize().background(backgroundDark)) {//backgroundDark
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "Diagnostic History",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Review and manage your vehicle's health logs",
                fontSize = 10.sp,
                color = onSurfaceVariantDark
            )

            Spacer(modifier = Modifier.height(24.dp))

            DiagnosticSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                primaryColor = primaryColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            DiagnosticFilterChips(
                filters = filters,
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it },
                primaryColor = primaryColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Log list
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                mockLogs.forEach { log ->
                    DiagnosticLogCard(
                        log = log,
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            LoadOlderLogsButton()
        }
    }
}
