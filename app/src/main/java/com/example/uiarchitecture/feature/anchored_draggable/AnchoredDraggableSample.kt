package com.example.uiarchitecture.feature.anchored_draggable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.uiarchitecture.R
import kotlin.math.roundToInt

//enum class DragAnchors {
//    Start,
//    End,
//}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnchoredDraggableSample(modifier: Modifier = Modifier,) {
    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            // 2
            initialValue = DragAnchors.Start,
            // 3
            positionalThreshold = { distance: Float -> distance * 0.5f },
            // 4
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            // 5
            animationSpec = tween(),
        ).apply {
            // 6
            updateAnchors(
                // 7
                DraggableAnchors {
                    DragAnchors.Start at 0f
                    DragAnchors.End at 400f
                }
            )
        }
    }
    Box(
        modifier = modifier.background(color = Color.Magenta),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .size(80.dp)
                // 1
                .offset {
                    IntOffset(
                        // 2
                        x = state.requireOffset().roundToInt(),
                        y = 0,
                    )
                }
                // 3
                .anchoredDraggable(state, Orientation.Horizontal),
            contentDescription = null,
        )
    }
}