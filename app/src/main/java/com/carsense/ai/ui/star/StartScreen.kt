package com.carsense.ai.ui.star

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carsense.ai.ui.star.components.CreateAccountScreen
import com.carsense.ai.ui.star.components.LinkDeviceScreen
import com.carsense.ai.ui.star.components.WelcomeScreen
import com.carsense.ai.ui.theme.backgroundDark
@Preview
@Composable
fun StartScreen(onFinish: () -> Unit = {}) {
    var currentStep by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(backgroundDark)) {
        AnimatedContent(
            targetState = currentStep,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
            },
            label = "step_transition"
        ) { step ->
            when (step) {
                0 -> WelcomeScreen(
                    onGetStarted = { currentStep = 1 }
                )
                1 -> CreateAccountScreen(
                    onContinueWithEmail = { currentStep = 2 },
                    onSkip = { currentStep = 2 }
                )
                2 -> LinkDeviceScreen(
                    onFinish = onFinish
                )
            }
        }
    }
}
