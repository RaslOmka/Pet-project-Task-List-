package com.ogorod.notepadtask.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class CustomShapes(
    val cornerCard: Shape,
    val cornerFAB: Shape
)

val LocalCustomShapes = staticCompositionLocalOf {
    CustomShapes(
        cornerCard = RoundedCornerShape(8.dp),
        cornerFAB = RoundedCornerShape(28.dp)
    )
}