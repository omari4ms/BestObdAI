package com.carsense.ai.ui.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.carsense.ai.ui.theme.onSurfaceDark
import com.carsense.ai.ui.theme.onSurfaceVariantDark
import com.carsense.ai.ui.theme.surfaceContainerLow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.sin

private data class MenuItemInfo(
    val title: String,
    val icon: ImageVector? = null,
    val isPro: Boolean = false
)

@Composable
fun MenuIcons(onDragStateChange: (Boolean) -> Unit = {}, onIconClick: (String) -> Unit = {}) {

    val initialActive = remember {
        listOf(
            MenuItemInfo("CLI",               Icons.Outlined.Terminal),
            MenuItemInfo("USER GUIDE",        Icons.AutoMirrored.Outlined.MenuBook),
            MenuItemInfo("MONITORING",        Icons.Outlined.Visibility),
            MenuItemInfo("VEHICLE DIAGNOSIS", Icons.Outlined.Build),
            MenuItemInfo("DASHBOARD",         Icons.Outlined.Dashboard),
            MenuItemInfo("MANUFACTURER DATA", Icons.Outlined.Factory),
            MenuItemInfo("DRIVING RECORD",    Icons.Outlined.History),
            MenuItemInfo("DRIVING LOG",       Icons.Outlined.EditNote),
            MenuItemInfo("PRO UPGRADE",       Icons.Outlined.WorkspacePremium),
            MenuItemInfo("DRIVING STYLE",     Icons.Outlined.Psychology),
        )
    }
    val initialAvailable = remember {
        listOf(
            MenuItemInfo("MAINTENANCE LOG", Icons.Outlined.HomeRepairService),
            MenuItemInfo("FUEL EFFICIENCY", Icons.Outlined.Eco),
            MenuItemInfo("FAQ",             Icons.AutoMirrored.Outlined.HelpOutline),
            MenuItemInfo("MAP",             Icons.Outlined.Map),
            MenuItemInfo("PART MILEAGE",    Icons.Outlined.Addchart),
        )
    }

    val activeItems    = remember { mutableStateListOf<MenuItemInfo>().apply { addAll(initialActive) } }
    val availableItems = remember { mutableStateListOf<MenuItemInfo>().apply { addAll(initialAvailable) } }

    var isEditing by remember { mutableStateOf(false) }

    val scope   = rememberCoroutineScope()
    val haptic  = LocalHapticFeedback.current
    val density = LocalDensity.current

    var wiggleTick by remember { mutableStateOf(0f) }
    LaunchedEffect(isEditing) {
        if (isEditing) {
            while (true) {
                wiggleTick += 0.18f
                kotlinx.coroutines.delay(16)
            }
        } else {
            wiggleTick = 0f
        }
    }

    // ── Drag State ────────────────────────────────────────────────────────────
    var draggedTitle by remember { mutableStateOf<String?>(null) }
    var draggedIndex by remember { mutableStateOf<Int?>(null) }

    val floatPos = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    var touchOffset by remember { mutableStateOf(Offset.Zero) }
    var draggedCardBounds by remember { mutableStateOf(Rect.Zero) }

    val isDragging by remember { derivedStateOf { draggedTitle != null } }

    val cardBounds = remember { HashMap<String, Rect>() }
    var gridCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }

    fun resetDrag() {
        draggedTitle = null
        draggedIndex = null
        touchOffset  = Offset.Zero
        onDragStateChange(false)
    }

    val buttonBgColor by animateColorAsState(
        targetValue = if (isEditing) Color(0xFF2E7D32) else Color.Black, label = "bg"
    )
    val buttonContentColor by animateColorAsState(
        targetValue = if (isEditing) Color.White else Color.Gray, label = "fg"
    )
    val buttonScale by animateFloatAsState(
        targetValue = if (isEditing) 1.05f else 1f, label = "bscale"
    )

    Column(modifier = Modifier.fillMaxWidth()) {

        Column {
            Row(
                modifier = Modifier
                    .clickable {
                        isEditing = !isEditing
                        if (!isEditing) resetDrag()
                    }
                    .graphicsLayer { scaleX = buttonScale; scaleY = buttonScale }
                    .background(buttonBgColor, RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector        = if (isEditing) Icons.Default.Check else Icons.AutoMirrored.Filled.MenuOpen,
                    contentDescription = null,
                    tint               = buttonContentColor,
                    modifier           = Modifier.size(16.dp)
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    text       = if (isEditing) "Save tiles" else "Edit tiles",
                    color      = buttonContentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 10.sp
                )
            }
            AnimatedVisibility(isEditing, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) {
                Text("Hold and drag to arrange", color = Color.Gray, fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Unified Parent Container (Handles all drag gestures globally) ─────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { gridCoords = it }
                .pointerInput(isEditing) {
                    if (!isEditing) return@pointerInput

                    // Single detector on parent completely prevents premature/auto dropping!
                    detectDragGesturesAfterLongPress(
                        onDragStart = { localOffset ->
                            // Look up which card bounds contains this initial touch coordinate
                            val hitEntry = cardBounds.entries.find { it.value.contains(localOffset) }
                            if (hitEntry != null) {
                                val itemTitle = hitEntry.key
                                val idx = activeItems.indexOfFirst { it.title == itemTitle }
                                if (idx != -1) {
                                    val bounds = hitEntry.value
                                    draggedTitle = itemTitle
                                    draggedIndex = idx
                                    draggedCardBounds = bounds
                                    touchOffset = localOffset - bounds.topLeft
                                    scope.launch { floatPos.snapTo(bounds.topLeft) }
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    onDragStateChange(true)
                                }
                            }
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            val ci = draggedIndex ?: return@detectDragGesturesAfterLongPress
                            scope.launch { floatPos.snapTo(floatPos.value + dragAmount) }

                            val floatCenter = floatPos.value + Offset(
                                draggedCardBounds.width / 2f,
                                draggedCardBounds.height / 2f
                            )
                            for (i in activeItems.indices) {
                                if (i == ci) continue
                                val ob = cardBounds[activeItems[i].title] ?: continue
                                if (ob.deflate(ob.width * 0.15f).contains(floatCenter)) {
                                    val temp = activeItems[ci]
                                    activeItems[ci] = activeItems[i]
                                    activeItems[i] = temp
                                    draggedIndex = i
                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    break
                                }
                            }
                        },
                        onDragEnd = {
                            val target = if (draggedTitle != null) cardBounds[draggedTitle] else null
                            if (target != null) {
                                scope.launch {
                                    floatPos.animateTo(
                                        target.topLeft,
                                        spring(dampingRatio = 0.65f, stiffness = Spring.StiffnessMedium)
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
        ) {
            val cols      = 3
            val rowCount  = (activeItems.size + cols - 1) / cols

            Column(
                modifier            = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                for (row in 0 until rowCount) {
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        for (col in 0 until cols) {
                            val flatIdx = row * cols + col
                            if (flatIdx < activeItems.size) {
                                val item          = activeItems[flatIdx]
                                val isThisDragged = item.title == draggedTitle

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .onGloballyPositioned { coords: LayoutCoordinates ->
                                            gridCoords?.let { g ->
                                                if (g.isAttached && coords.isAttached) {
                                                    cardBounds[item.title] = g.localBoundingBoxOf(coords)
                                                }
                                            }
                                        },
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    if (isThisDragged) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(120.dp)
                                                .border(
                                                    width = 1.5.dp,
                                                    color = Color.White.copy(alpha = 0.20f),
                                                    shape = RoundedCornerShape(28.dp)
                                                )
                                                .background(
                                                    color = Color.White.copy(alpha = 0.04f),
                                                    shape = RoundedCornerShape(28.dp)
                                                )
                                        )
                                    } else {
                                        val phaseShift = flatIdx * 0.55f
                                        MenuCard(
                                            item        = item,
                                            isEditing   = isEditing,
                                            isAvailable = false,
                                            isDimmed    = isDragging,
                                            wiggleTick  = if (isEditing && !isDragging) wiggleTick else 0f,
                                            wigglePhase = phaseShift,
                                            onDelete    = {
                                                cardBounds.remove(item.title)
                                                activeItems.remove(item)
                                                availableItems.add(item)
                                            },
                                            onAdd = {},
                                            onIconClick = { onIconClick(item.title) }
                                        )
                                    }
                                }
                            } else {
                                Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            // ── Floating overlay ──────────────────────────────────────────────
            if (isDragging) {
                val dragId   = draggedTitle
                val dragItem = if (dragId != null) activeItems.firstOrNull { it.title == dragId } else null
                if (dragItem != null) {
                    val px = floatPos.value.x
                    val py = floatPos.value.y
                    Box(
                        modifier = Modifier
                            .offset { IntOffset(px.roundToInt(), py.roundToInt()) }
                            .size(
                                width  = with(density) { draggedCardBounds.width.toDp() },
                                height = with(density) { draggedCardBounds.height.toDp() }
                            )
                            .zIndex(10f)
                            .shadow(elevation = 28.dp, shape = RoundedCornerShape(28.dp), clip = false)
                            .clip(RoundedCornerShape(28.dp))
                            .graphicsLayer {
                                scaleX = 1.08f
                                scaleY = 1.08f
                            }
                    ) {
                        MenuCard(
                            item        = dragItem,
                            isEditing   = false,
                            isAvailable = false,
                            isDimmed    = false,
                            wiggleTick  = 0f,
                            wigglePhase = 0f,
                            onDelete    = {},
                            onAdd       = {},
                            onIconClick = { onIconClick(dragItem.title) }
                        )
                    }
                }
            }
        }

        // ── Available items ───────────────────────────────────────────────────
        AnimatedVisibility(isEditing, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(28.dp))
                Text(
                    "More functions",
                    color         = Color.Gray,
                    fontWeight    = FontWeight.Bold,
                    fontSize      = 11.sp,
                    letterSpacing = 1.sp,
                    modifier      = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(Modifier.height(14.dp))

                val avCols     = 3
                val avRowCount = (availableItems.size + avCols - 1) / avCols
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    for (row in 0 until avRowCount) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            for (col in 0 until avCols) {
                                val idx = row * avCols + col
                                if (idx < availableItems.size) {
                                    val item = availableItems[idx]
                                    MenuCard(
                                        item        = item,
                                        isEditing   = true,
                                        isAvailable = true,
                                        isDimmed    = false,
                                        wiggleTick  = 0f,
                                        wigglePhase = 0f,
                                        onDelete    = {},
                                        onAdd       = {
                                            availableItems.remove(item)
                                            activeItems.add(item)
                                        },
                                        onIconClick = { onIconClick(item.title) },
                                        modifier    = Modifier.weight(1f)
                                    )
                                } else {
                                    Spacer(Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
@Composable
private fun MenuCard(
    item:        MenuItemInfo,
    isEditing:   Boolean,
    isAvailable: Boolean,
    isDimmed:    Boolean,
    wiggleTick:  Float,
    wigglePhase: Float,
    onDelete:    () -> Unit,
    onAdd:       () -> Unit,
    onIconClick: () -> Unit = {},
    modifier:    Modifier = Modifier
) {
    val wiggleDeg = if (wiggleTick != 0f)
        (sin((wiggleTick + wigglePhase).toDouble()) * 2.2f).toFloat()
    else 0f

    val alpha by animateFloatAsState(
        targetValue   = if (isDimmed) 0.40f else 1f,
        animationSpec = tween(durationMillis = 180),
        label         = "alpha"
    )

    Box(
        modifier         = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .graphicsLayer {
                    rotationZ       = wiggleDeg
                    this.alpha      = alpha
                    scaleX          = if (isDimmed) 0.97f else 1f
                    scaleY          = if (isDimmed) 0.97f else 1f
                }
                .background(surfaceContainerLow.copy(alpha = 0.5f), RoundedCornerShape(28.dp))
                .clickable(enabled = !isEditing) { onIconClick() }
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item.icon?.let {
                Icon(
                    imageVector        = it,
                    contentDescription = item.title,
                    tint               = onSurfaceVariantDark,
                    modifier           = Modifier.size(36.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text          = item.title,
                color         = onSurfaceDark,
                fontWeight    = FontWeight.Bold,
                fontSize      = 9.sp,
                textAlign     = TextAlign.Center,
                letterSpacing = 0.5.sp,
                lineHeight    = 12.sp
            )
        }

        // ── Remove Badge ──────────────────────────────────────────────────────
        if (isEditing) {
            Box(
                modifier = Modifier
                    .offset(x = 6.dp, y = (-6).dp)
                    .size(24.dp)
                    .graphicsLayer { rotationZ = -wiggleDeg }
                    .background(
                        color = if (isAvailable) Color(0xFF2E7D32) else Color(0xFFC62828),
                        shape = CircleShape
                    )
                    // Regular clickable logic works clean now because parent intercepts
                    // long-press seamlessly across boundaries!
                    .clickable {
                        if (isAvailable) onAdd() else onDelete()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = if (isAvailable) Icons.Outlined.Add else Icons.Outlined.Remove,
                    contentDescription = null,
                    tint               = Color.White,
                    modifier           = Modifier.size(14.dp)
                )
            }
        }
    }
}