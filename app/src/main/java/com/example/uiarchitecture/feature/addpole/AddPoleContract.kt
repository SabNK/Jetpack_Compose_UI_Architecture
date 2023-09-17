package com.example.uiarchitecture.feature.addpole

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents AddPoleScreen
 **/
data class AddPoleState(
    val poleName: String = "",
    val geo: String = "",
    val ava64: String = ""
)

/**
 * AddPole Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class AddPoleActions(
    val onPoleNameEdited: () -> Unit = {},
    val onGeoClick: () -> Unit = {},
    val onAvaClick: () -> Unit = {},
    val onFabClick: () -> Unit = {}
)

/**
 * Compose Utility to retrieve actions from nested components
 **/
val LocalAddPoleActions = staticCompositionLocalOf<AddPoleActions> {
    error("{NAME} Actions Were not provided, make sure ProvideAddPoleActions is called")
}

@Composable
fun ProvideAddPoleActions(actions: AddPoleActions, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAddPoleActions provides actions) {
        content.invoke()
    }
}

