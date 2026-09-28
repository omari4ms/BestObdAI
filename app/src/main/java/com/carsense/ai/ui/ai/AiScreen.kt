package com.carsense.ai.ui.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.carsense.ai.ui.ai.components.*
import com.carsense.ai.ui.theme.backgroundDark
import com.carsense.ai.ui.theme.primaryContainerDark
import com.carsense.ai.ui.theme.secondaryDark
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AiScreen() {
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val isKeyboardOpen = WindowInsets.isImeVisible

    LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
            listState.animateScrollToItem(4)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
    ) {
        // --- Background Aesthetics ---
        
        // Ambient Glows
        Box(
            modifier = Modifier
                .size(400.dp)
                .offset(x = 200.dp, y = (-100).dp)
                .blur(120.dp)
                .background(primaryContainerDark.copy(alpha = 0.08f), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .size(350.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-100).dp, y = 100.dp)
                .blur(100.dp)
                .background(secondaryDark.copy(alpha = 0.12f), shape = CircleShape)
        )

        // Subtle Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.2f)
                        )
                    )
                )
        )

        // --- Content ---
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { Spacer(modifier = Modifier.height(10.dp)) }

                item {
                    AiMessageBubble(
                        text = "Why is my engine making a shaking sound?",
                        type = MessageType.USER
                    )
                }

                item { 
                    AiMessageBubble(
                        text = "A shacking sound can indicate a few things, like low oil level or an issue with the valve train.",
                        type = MessageType.AI,
                        telemetryContent = "Based on your recent telemetry, your oil pressure is within normal range, but I've detected a slight vibration in cylinder 3."
                    )
                }

                item {
                    QuickActionChips(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item { Spacer(modifier = Modifier.height(10.dp)) }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                ChatInputField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    onSendClick = {
                        if (inputText.isNotBlank()) {
                            inputText = ""
                        }
                    }
                )
            }
        }
        }
    }

