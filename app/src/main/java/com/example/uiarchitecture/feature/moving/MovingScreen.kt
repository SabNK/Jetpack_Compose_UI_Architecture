package com.example.uiarchitecture.feature.moving

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovingScreen(
    state: MovingState = MovingState(),
    actions: MovingActions = MovingActions()
) {
    // TODO UI Logic
}

@Composable
@Preview(name = "Moving")
private fun MovingScreenPreview() {
    MovingScreen()
}

