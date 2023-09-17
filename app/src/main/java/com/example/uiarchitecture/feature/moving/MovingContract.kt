package com.example.uiarchitecture.feature.moving

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents MovingScreen
 **/
class MovingState(
    val name: String = "",
    val coord: String = "",
    val avatar: String = ""
)

/**
 * Moving Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MovingActions(
    val onNameChanged: () -> Unit = {},
    val onCoordClicked: () -> Unit = {},
    val onAvatarClicked: () -> Unit = {},
    val onFabClicked: () -> Unit = {}
)

/**
 * Compose Utility to retrieve actions from nested components
 **/
val LocalMovingActions = staticCompositionLocalOf<MovingActions> {
    error("{NAME} Actions Were not provided, make sure ProvideMovingActions is called")
}

@Composable
fun ProvideMovingActions(actions: MovingActions, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalMovingActions provides actions) {
        content.invoke()
    }
}

