package com.carsense.ai.ui.dashboard

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carsense.ai.ui.dashboard.components.AutoSlidingPager
import com.carsense.ai.ui.dashboard.components.MenuIcons
import com.carsense.ai.ui.dashboard.components.pages.*
import com.carsense.ai.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun DashboardScreen() {
    val primaryColor = MaterialTheme.colorScheme.primaryContainer
    val secondaryColor = MaterialTheme.colorScheme.secondary
    var isDragging by remember { mutableStateOf(false) }
    var selectedPage by remember { mutableStateOf<String?>(null) }

    AnimatedContent(
        targetState = selectedPage,
        transitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
        },
        label = "page_transition"
    ) { page ->
        if (page != null) {
            BackHandler { selectedPage = null }
            Box(modifier = Modifier.fillMaxSize().background(backgroundDark)) {
                when (page) {
                    "CLI" -> CliPage()
                    "USER GUIDE" -> UserGuidePage()
                    "MONITORING" -> MonitoringPage()
                    "VEHICLE DIAGNOSIS" -> VehicleDiagnosisPage()
                    "DASHBOARD" -> DashboardPage()
                    "MANUFACTURER DATA" -> ManufacturerDataPage()
                    "DRIVING RECORD" -> DrivingRecordPage()
                    "DRIVING LOG" -> DrivingLogPage()
                    "PRO UPGRADE" -> ProUpgradePage()
                    "DRIVING STYLE" -> DrivingStylePage()
                    "MAINTENANCE LOG" -> MaintenanceLogPage()
                    "FUEL EFFICIENCY" -> FuelEfficiencyPage()
                    "FAQ" -> FaqPage()
                    "MAP" -> MapPage()
                    "PART MILEAGE" -> PartMileagePage()
                    else -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        androidx.compose.material3.Text("Unknown Page", color = androidx.compose.ui.graphics.Color.White)
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize().background(backgroundDark)) {
                // Scrollable Main Content
                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState(), enabled = !isDragging)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Greeting Section
                    Spacer(modifier = Modifier.height(10.dp))
                    AutoSlidingPager()
                    Spacer(modifier = Modifier.height(10.dp))
                    MenuIcons(
                        onDragStateChange = { isDragging = it },
                        onIconClick = { selectedPage = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


