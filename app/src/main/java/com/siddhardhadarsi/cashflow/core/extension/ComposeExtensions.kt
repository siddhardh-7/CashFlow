package com.siddhardhadarsi.cashflow.core.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

// Custom clickable with ripple effect
//fun Modifier.clickableWithRipple(
//    bounded: Boolean = true,
//    radius: Dp = Dp.Unspecified,
//    color: Color = Color.Unspecified,
//    onClick: () -> Unit
//) = composed {
//    val ripple = rememberRipple(
//        bounded = bounded,
//        radius = radius,
//        color = if (color == Color.Unspecified) {
//            MaterialTheme.colorScheme.primary
//        } else color
//    )
//
//    clickable(
//        interactionSource = remember { MutableInteractionSource() },
//        indication = ripple,
//        onClick = onClick
//    )
//}

// Conditional modifier
fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier = if (condition) {
    then(modifier(Modifier))
} else {
    this
}

// Safe clickable that prevents multiple rapid clicks
fun Modifier.safeClickable(
    debounceTime: Long = 500L,
    onClick: () -> Unit
) = composed {
    var lastClickTime = remember { 0L }

    clickable {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > debounceTime) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

// Shimmer effect for loading states
fun Modifier.shimmer(
    visible: Boolean = true,
    color: Color = Color.Gray.copy(alpha = 0.3f),
    shape: Shape? = null
) = composed {
    if (visible && shape != null) {
        clip(shape)
    } else {
        this
    }
}

// Convert dp to pixels
@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) { toPx() }
}

// Convert pixels to dp
@Composable
fun Float.toDp(): Dp {
    return with(LocalDensity.current) { toDp() }
}