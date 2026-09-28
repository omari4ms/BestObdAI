package com.carsense.ai.ui.diagnostic.components

import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.Modifier

import com.carsense.ai.ui.theme.errorDark
import com.carsense.ai.ui.theme.onSurfaceVariantDark

fun getColorById(id: Int, contextColor1: Color, contextColor2: Color): Color {
    return when (id) {
        1 -> contextColor1
        2 -> contextColor2
        3 -> onSurfaceVariantDark
        4 -> errorDark
        else -> Color.White
    }
}

fun Modifier.glow(
    color: Color,
    alpha: Float = 0.6f,
    radius: Float = 8f,
    isVisible: Boolean = true
) = this.drawBehind {
    if (!isVisible) return@drawBehind
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha = 0f).toArgb()
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparent
        frameworkPaint.setShadowLayer(radius, 0f, 0f, shadowColor)
        it.drawCircle(
            center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2),
            radius = size.width / 2,
            paint = paint
        )
    }
}
