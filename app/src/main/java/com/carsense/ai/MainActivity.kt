package com.carsense.ai

import dagger.hilt.android.AndroidEntryPoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.carsense.ai.device.permissions.AppPermissionsHandler
import com.carsense.ai.ui.ai.AiScreen
import com.carsense.ai.ui.dashboard.DashboardScreen
import com.carsense.ai.ui.dashboard.components.BottomNavBar
import com.carsense.ai.ui.dashboard.components.TopNavBar
import com.carsense.ai.ui.profile.ProfileScreen
import com.carsense.ai.ui.theme.CarSenseTheme
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.secondaryDark

enum class Screen {
    Dashboard, Diagnostic, Ai, Profile, ConnectionChoice
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            var hasCompletedOnboarding by remember { mutableStateOf(sharedPref.getBoolean("completed_onboarding", false)) }

            var primaryThemeColor by remember { mutableStateOf(primaryContainerDark) }
            var secondaryThemeColor by remember { mutableStateOf(secondaryDark) }
            var currentScreen by remember { mutableStateOf(Screen.Dashboard) }

            val isKeyboardOpen = WindowInsets.isImeVisible
            val showAiBackOnly = currentScreen == Screen.Ai && isKeyboardOpen

            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            CarSenseTheme(
                primaryColor = primaryThemeColor,
                secondaryColor = secondaryThemeColor
            ) {
                var allPermissionsGranted by remember { mutableStateOf(false) }

                if (!allPermissionsGranted) {
                    AppPermissionsHandler(
                        onAllPermissionsGranted = { allPermissionsGranted = true }
                    )
                } else {
                    if (!hasCompletedOnboarding) {
                        com.carsense.ai.ui.star.StartScreen(onFinish = {
                            sharedPref.edit().putBoolean("completed_onboarding", true).apply()
                            hasCompletedOnboarding = true
                        })
                    } else {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                showBackButtonOnly = showAiBackOnly,
                                onBackClick = {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                },
                                onBluetoothConnectClick = {
                                    currentScreen = Screen.ConnectionChoice
                                }
                            )
                        },
                        bottomBar = {
                            if (!showAiBackOnly) {
                                BottomNavBar(
                                    selectedScreen = if (currentScreen == Screen.ConnectionChoice) Screen.Dashboard else currentScreen,
                                    onScreenSelected = { currentScreen = it }
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.fillMaxSize().background(Color.Black).padding(innerPadding)) {
                            // Screen Content
                            when (currentScreen) {
                                Screen.Dashboard -> DashboardScreen()
                                Screen.Diagnostic -> com.carsense.ai.ui.diagnostic.DiagnosticScreen()
                                Screen.Profile -> ProfileScreen(
                                    primaryColor = primaryThemeColor,
                                    secondaryColor = secondaryThemeColor,
                                    onPrimaryColorSelected = { primaryThemeColor = it },
                                    onSecondaryColorSelected = { secondaryThemeColor = it }
                                )
                                Screen.Ai -> AiScreen()
                                Screen.ConnectionChoice -> com.carsense.ai.ui.device.ConnectionChose(
                                    onBack = { currentScreen = Screen.Dashboard }
                                )
                            }
                        }
                    }
                    }
                }
            }
        }
    }
}
