package com.example.uiarchitecture.feature.moving

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MovingRoute(
    coordinator: MovingCoordinator = rememberMovingCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(MovingState())

    // UI Actions
    val actions = rememberMovingActions(coordinator)

    // UI Rendering
    MovingScreen(uiState, actions)
}


@Composable
fun rememberMovingActions(coordinator: MovingCoordinator): MovingActions {
    return remember(coordinator) {
        MovingActions(
            onClick = coordinator::doStuff
        )
    }
}