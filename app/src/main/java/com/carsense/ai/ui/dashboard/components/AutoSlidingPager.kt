package com.carsense.ai.ui.dashboard.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PausePresentation
import androidx.compose.material.icons.filled.Slideshow
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.carsense.ai.R
import com.carsense.ai.ui.dashboard.components.widgets.ConsumptionChartCard
import com.carsense.ai.ui.dashboard.components.widgets.HeroSpeedGauge
import com.carsense.ai.ui.dashboard.components.widgets.TirePressureCard
import com.carsense.ai.ui.theme.onSurfaceDark
import com.carsense.ai.ui.theme.surfaceContainerHigh
import com.carsense.ai.ui.theme.surfaceContainerLow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

// ── Model ─────────────────────────────────────────────────────────────────────
private data class PagerWidgetInfo(
    val id: String,
    val title: String,
    val previewRes: Int,
    val isEnabled: Boolean = true
)

private val DEFAULT_WIDGETS = listOf(
    PagerWidgetInfo("tire_pressure",     "TYRE PRESSURE",     R.drawable.preview_tire_pressure),
    PagerWidgetInfo("consumption_chart", "CONSUMPTION CHART", R.drawable.preview_consumption_chart),
    PagerWidgetInfo("car_visualizer",    "CAR VISUALIZER",    R.drawable.preview_car_visualizer),
    PagerWidgetInfo("speed_gauge",       "SPEED GAUGE",       R.drawable.preview_speed_gauge),
)

// ── Main Composable ───────────────────────────────────────────────────────────
@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingPager() {
    val primaryColor   = MaterialTheme.colorScheme.primaryContainer
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val scope          = rememberCoroutineScope()
    val haptic         = LocalHapticFeedback.current
    val density        = LocalDensity.current

    val widgets        = remember { mutableStateListOf<PagerWidgetInfo>().apply { addAll(DEFAULT_WIDGETS) } }
    val enabledWidgets = widgets.filter { it.isEnabled }

    val pagerState = rememberPagerState(pageCount = { enabledWidgets.size })

    var isAutoScrollEnabled by remember { mutableStateOf(true) }
    var isEditingWidgets    by remember { mutableStateOf(false) }
    var editSnapshot        by remember { mutableStateOf<List<PagerWidgetInfo>>(emptyList()) }

    // ── Drag state ────────────────────────────────────────────────────────────
    // draggedId / draggedIndex: which card is being dragged
    var draggedId    by remember { mutableStateOf<String?>(null) }
    var draggedIndex by remember { mutableStateOf<Int?>(null) }

    // floatPos: absolute position of the floating ghost card in grid coordinates.
    // Stored as plain state; read only inside graphicsLayer (draw phase) → no recomposition on move.
    val floatPos = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    // touchOffset: where inside the card the finger landed (so card doesn't snap its center to finger)
    var touchOffset  by remember { mutableStateOf(Offset.Zero) }

    val isDragging by remember { derivedStateOf { draggedId != null } }

    // cardBounds: plain HashMap, never read during composition → no snapshot overhead
    val cardBounds   = remember { HashMap<String, Rect>() }
    // snapshot of card size at drag-start, used to size the overlay ghost
    var draggedCardSize by remember { mutableStateOf(Rect.Zero) }

    var gridCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }

    fun resetDrag() {
        draggedId    = null
        draggedIndex = null
        touchOffset  = Offset.Zero
    }
    fun cancelEdit() {
        widgets.clear(); widgets.addAll(editSnapshot); resetDrag()
        isEditingWidgets = false; isAutoScrollEnabled = true
    }
    fun saveEdit() {
        resetDrag(); isEditingWidgets = false; isAutoScrollEnabled = true
        scope.launch { pagerState.scrollToPage(0) }
    }

    // Auto-scroll loop
    LaunchedEffect(isAutoScrollEnabled, enabledWidgets.size) {
        while (isAutoScrollEnabled && enabledWidgets.isNotEmpty()) {
            delay(5000)
            if (!pagerState.isScrollInProgress) {
                val next = (pagerState.currentPage + 1) % enabledWidgets.size
                pagerState.animateScrollToPage(next, animationSpec = tween(600))
            }
        }
    }

    // ── Edit sheet ────────────────────────────────────────────────────────────
    if (isEditingWidgets) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress      = false,
                dismissOnClickOutside   = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.92f)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(Color(0xFF131313))
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                ) {
                    // Drag-handle pill
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp)
                            .size(40.dp, 4.dp)
                            .background(Color.Gray.copy(0.35f), CircleShape)
                    )

                    // Header row
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text("Edit Widgets", color = onSurfaceDark, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.weight(1f))
                        Row(
                            modifier = Modifier
                                .background(Color(0xFF3A1C1C), RoundedCornerShape(20.dp))
                                .clickable { cancelEdit() }
                                .padding(10.dp, 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Close, null, tint = Color(0xFFFF6B6B), modifier = Modifier.size(14.dp))
                            Spacer(Modifier.size(4.dp))
                            Text("Cancel", color = Color(0xFFFF6B6B), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                        Spacer(Modifier.size(8.dp))
                        Row(
                            modifier = Modifier
                                .background(Color(0xFF1C3A1C), RoundedCornerShape(20.dp))
                                .clickable { saveEdit() }
                                .padding(10.dp, 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Check, null, tint = Color(0xFF6BFF6B), modifier = Modifier.size(14.dp))
                            Spacer(Modifier.size(4.dp))
                            Text("Save", color = Color(0xFF6BFF6B), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        }
                    }

                    Spacer(Modifier.height(6.dp))
                    Text("Hold & drag to reorder. Toggle switches to enable/disable.", color = Color.Gray, fontSize = 11.sp)
                    Spacer(Modifier.height(16.dp))

                    // ── Grid + floating overlay in a shared Box ───────────────
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        // ── Static grid (no translationX/Y on items ever) ─────
                        LazyVerticalGrid(
                            columns               = GridCells.Fixed(2),
                            verticalArrangement   = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            userScrollEnabled     = !isDragging,
                            contentPadding        = PaddingValues(bottom = 40.dp),
                            modifier              = Modifier
                                .fillMaxSize()
                                .onGloballyPositioned { gridCoords = it }
                        ) {
                            items(widgets, key = { it.id }) { item ->
                                val isThisDragged = item.id == draggedId
                                val overallIdx    = widgets.indexOfFirst { it.id == item.id }

                                // Gesture handler — only for long-press drag
                                val gestureMod = Modifier.pointerInput(item.id) {
                                    detectDragGesturesAfterLongPress(
                                        onDragStart = { localOffset ->
                                            val idx = widgets.indexOfFirst { it.id == item.id }
                                            if (idx == -1) return@detectDragGesturesAfterLongPress
                                            val bounds = cardBounds[item.id] ?: return@detectDragGesturesAfterLongPress

                                            draggedId        = item.id
                                            draggedIndex     = idx
                                            draggedCardSize  = bounds
                                            // Where in the card the finger landed
                                            touchOffset      = localOffset
                                            // Snap float position to card's current grid position immediately
                                            scope.launch {
                                                floatPos.snapTo(bounds.topLeft)
                                            }
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            val ci = draggedIndex ?: return@detectDragGesturesAfterLongPress
                                            val ct = draggedId    ?: return@detectDragGesturesAfterLongPress

                                            // Move the overlay card — snapTo keeps it frame-perfect under the finger
                                            scope.launch {
                                                floatPos.snapTo(floatPos.value + dragAmount)
                                            }

                                            // Hit-test using the overlay card's center
                                            val floatCenter = floatPos.value + Offset(
                                                draggedCardSize.width / 2f,
                                                draggedCardSize.height / 2f
                                            )

                                            for (i in widgets.indices) {
                                                if (i == ci) continue
                                                val ob = cardBounds[widgets[i].id] ?: continue
                                                // Trigger swap when float center crosses into inner 70% of target
                                                val inset = ob.deflate(ob.width * 0.15f)
                                                if (inset.contains(floatCenter)) {
                                                    val temp     = widgets[ci]
                                                    widgets[ci]  = widgets[i]
                                                    widgets[i]   = temp
                                                    draggedIndex = i
                                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                                    break
                                                }
                                            }
                                        },
                                        onDragEnd = {
                                            val ct = draggedId
                                            val targetBounds = if (ct != null) cardBounds[ct] else null
                                            if (targetBounds != null) {
                                                // Snap-back animation to the card's new slot
                                                scope.launch {
                                                    floatPos.animateTo(
                                                        targetBounds.topLeft,
                                                        animationSpec = spring(
                                                            dampingRatio = 0.7f,
                                                            stiffness    = Spring.StiffnessMediumLow
                                                        )
                                                    )
                                                    resetDrag()
                                                }
                                            } else {
                                                resetDrag()
                                            }
                                        },
                                        onDragCancel = { resetDrag() }
                                    )
                                }

                                // Grid slot — when dragged, show ghost placeholder only
                                Box(
                                    modifier = Modifier
                                        .then(gestureMod)
                                        .onGloballyPositioned { coords: LayoutCoordinates ->
                                            gridCoords?.let { g ->
                                                if (g.isAttached && coords.isAttached)
                                                    cardBounds[item.id] = g.localBoundingBoxOf(coords)
                                            }
                                        }
                                ) {
                                    if (isThisDragged) {
                                        // Ghost placeholder while card is floating
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(180.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(Color(0xFF1A1A2E))
                                                .border(
                                                    1.5.dp,
                                                    MaterialTheme.colorScheme.primaryContainer.copy(0.35f),
                                                    RoundedCornerShape(16.dp)
                                                )
                                        )
                                    } else {
                                        MiniWidgetCard(
                                            item            = item,
                                            pageNum         = if (item.isEnabled) enabledWidgets.indexOf(item) + 1 else null,
                                            isDimmed        = isDragging,
                                            onToggleEnabled = {
                                                if (overallIdx != -1)
                                                    widgets[overallIdx] = item.copy(isEnabled = !item.isEnabled)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        // ── Floating overlay card — rendered ABOVE the grid ───
                        // Positioned absolutely; never clipped; tracks finger 1:1
                        if (isDragging) {
                            val dragId   = draggedId
                            val dragItem = if (dragId != null) widgets.firstOrNull { it.id == dragId } else null
                            if (dragItem != null) {
                                val px = floatPos.value.x
                                val py = floatPos.value.y
                                Box(
                                    modifier = Modifier
                                        .offset { IntOffset(px.roundToInt(), py.roundToInt()) }
                                        .size(
                                            width  = with(density) { draggedCardSize.width.toDp() },
                                            height = with(density) { draggedCardSize.height.toDp() }
                                        )
                                        .zIndex(10f)
                                        .shadow(24.dp, RoundedCornerShape(16.dp))
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(surfaceContainerHigh)
                                        .graphicsLayer {
                                            scaleX = 1.06f
                                            scaleY = 1.06f
                                        }
                                ) {
                                    MiniWidgetCard(
                                        item            = dragItem,
                                        pageNum         = if (dragItem.isEnabled) enabledWidgets.indexOf(dragItem) + 1 else null,
                                        isDimmed        = false,
                                        onToggleEnabled = { /* disabled during drag */ }
                                    )
                                }
                            }
                        }
                    } // end Box (grid + overlay)
                }
            }
        }
    }

    // ── Pager + Top Buttons ───────────────────────────────────────────────────
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (enabledWidgets.size > 1) {
                Row(
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(20.dp))
                        .clickable { isAutoScrollEnabled = !isAutoScrollEnabled }
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isAutoScrollEnabled) {
                        Icon(Icons.Default.PausePresentation, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                    } else {
                        Icon(Icons.Default.Slideshow, null, tint = primaryColor, modifier = Modifier.size(20.dp))
                    }
                    Spacer(Modifier.size(5.dp))
                    Text(
                        if (isAutoScrollEnabled) "Stop Sliding " else "Auto Sliding ",
                        color = if (isAutoScrollEnabled) Color.Gray else primaryColor,
                        fontWeight = FontWeight.Bold, fontSize = 10.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .background(Color.Black, RoundedCornerShape(20.dp))
                    .clickable {
                        editSnapshot        = widgets.toList()
                        isAutoScrollEnabled = false
                        isEditingWidgets    = true
                    }
                    .padding(10.dp, 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Widgets, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                Spacer(Modifier.size(5.dp))
                Text("Edit Widgets ", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            }
        }

        Spacer(Modifier.height(10.dp))

        if (enabledWidgets.isNotEmpty()) {
            Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))) {
                HorizontalPager(
                    state    = pagerState,
                    key      = { page -> enabledWidgets.getOrNull(page)?.id ?: page },
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (enabledWidgets.getOrNull(page)?.id) {
                        "tire_pressure"     -> TirePressureCard(
                            frWarning = true,
                            warningMessage = null
                        )

                        "consumption_chart" -> ConsumptionChartCard(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor
                        )

                        else                -> HeroSpeedGauge(speed = 55)
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center) {
                repeat(pagerState.pageCount) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp).size(8.dp)
                            .background(
                                if (pagerState.currentPage == index) primaryColor else Color.Gray,
                                CircleShape
                            )
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(surfaceContainerLow),
                contentAlignment = Alignment.Center
            ) {
                Text("All widgets disabled", color = Color.Gray, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ── Mini Widget Card ──────────────────────────────────────────────────────────
// Simplified: no drag offset params — the overlay Box handles positioning entirely.
@Composable
private fun MiniWidgetCard(
    item:            PagerWidgetInfo,
    pageNum:         Int?,
    isDimmed:        Boolean,
    onToggleEnabled: () -> Unit,
    modifier:        Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(surfaceContainerLow)
            .border(
                width = 1.5.dp,
                color = if (item.isEnabled) Color.White.copy(0.08f) else Color.Red.copy(0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .graphicsLayer {
                alpha = when {
                    isDimmed        -> 0.45f
                    !item.isEnabled -> 0.55f
                    else            -> 1f
                }
            }
    ) {
        Column(
            modifier            = Modifier.height(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                if (pageNum != null) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .background(Color.Black.copy(alpha = 0.75f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$pageNum", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .height(22.dp)
                            .background(Color.Red.copy(alpha = 0.2f), RoundedCornerShape(10.dp))
                            .border(1.dp, Color.Red.copy(0.5f), RoundedCornerShape(10.dp))
                            .padding(horizontal = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Off", color = Color.Red, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Switch(
                    checked         = item.isEnabled,
                    onCheckedChange = { onToggleEnabled() },
                    modifier        = Modifier.graphicsLayer(scaleX = 0.65f, scaleY = 0.65f),
                    colors          = SwitchDefaults.colors(
                        checkedThumbColor   = Color.White,
                        checkedTrackColor   = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.Black.copy(0.5f)
                    )
                )
            }

            Image(
                painter            = painterResource(id = item.previewRes),
                contentDescription = item.title,
                contentScale       = ContentScale.Fit,
                modifier           = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}