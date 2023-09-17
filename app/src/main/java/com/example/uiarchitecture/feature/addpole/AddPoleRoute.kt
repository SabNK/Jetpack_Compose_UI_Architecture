package com.example.uiarchitecture.feature.addpole

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun AddPoleRoute(
    coordinator: AddPoleCoordinator = com.example.uiarchitecture.feature.addpole.rememberAddPoleCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(AddPoleState())

    // UI Actions
    val actions = rememberAddPoleActions(coordinator)

    // UI Rendering
    AddPoleScreen(uiState, actions)
}


@Composable
fun rememberAddPoleActions(coordinator: AddPoleCoordinator): AddPoleActions {
    return remember(coordinator) {
        AddPoleActions(
            onPoleNameEdited = coordinator::onPoleNameEdited,
            onGeoClick = coordinator::onGeoClick,
        )
    }
}