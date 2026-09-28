package com.carsense.ai.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carsense.ai.ui.profile.components.*
import com.carsense.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
        primaryColor: Color,
        secondaryColor: Color,
        onPrimaryColorSelected: (Color) -> Unit,
        onSecondaryColorSelected: (Color) -> Unit
) {
    // Interface Style State
    var isDarkMode by remember { mutableStateOf(true) }
    var isGlassmorphismEnabled by remember { mutableStateOf(true) }

    // Units State
    var isMetric by remember { mutableStateOf(true) }

    // Localization State
    var selectedLanguage by remember { mutableStateOf("English (United States)") }
    var selectedTimeZone by remember { mutableStateOf("PST (UTC -8:00)") }

    // Bottom Sheet State
    var showBottomSheet by remember { mutableStateOf(false) }
    var editingPrimary by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()

    Column(
            modifier =
                    Modifier.fillMaxSize()
                            .background(backgroundDark)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 20.dp)
    ) {

        // Header
        ProfileHeader()

        UserProfileSection()

        // Bento Grid
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // Bluetooth Protocol
            BluetoothProtocolCard()

            // Interface Style
            InterfaceStyleCard(
                    isDarkMode = isDarkMode,
                    isGlassmorphismEnabled = isGlassmorphismEnabled,
                    onDarkModeToggle = { isDarkMode = !isDarkMode },
                    onGlassmorphismToggle = { isGlassmorphismEnabled = !isGlassmorphismEnabled }
            )

            // Color Palette
            ColorPaletteCard(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    onColorClick = { isPrimary -> 
                        editingPrimary = isPrimary
                        showBottomSheet = true 
                    },
                    onResetClick = {
                        onPrimaryColorSelected(Color(0xFF00FBFB))
                        onSecondaryColorSelected(Color(0xFFDAB9FF))
                    }
            )

            // Units
            UnitsCard(
                    isMetric = isMetric,
                    distanceUnit = if (isMetric) "Kilometers (km)" else "Miles (mi)",
                    pressureUnit = if (isMetric) "Bar" else "PSI",
                    onUnitToggle = { isMetric = !isMetric }
            )

            // Localization
            LocalizationCard(
                    selectedLanguage = selectedLanguage,
                    selectedTimeZone = selectedTimeZone,
                    onLanguageClick = { /* Handle language selection dialog */},
                    onTimeZoneClick = { /* Handle timezone selection dialog */}
            )

            // Danger Zone
            DangerZoneAction(onResetClick = { /* Handle core reset logic */})
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color(0xFF131313),
            dragHandle = { BottomSheetDefaults.DragHandle(color = Color.White.copy(alpha = 0.2f)) }
        ) {
            ColorPickerBottomSheet(
                initialColor = if (editingPrimary) primaryColor else secondaryColor,
                onColorApplied = { newColor ->
                    if (editingPrimary) onPrimaryColorSelected(newColor)
                    else onSecondaryColorSelected(newColor)
                    showBottomSheet = false
                }
            )
        }
    }
}
