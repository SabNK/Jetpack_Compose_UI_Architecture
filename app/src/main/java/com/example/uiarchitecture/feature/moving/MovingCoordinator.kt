package com.example.uiarchitecture.feature.moving

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel


/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MovingCoordinator(
    val viewModel: MovingViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberMovingCoordinator(
    viewModel: MovingViewModel = hiltViewModel()
): MovingCoordinator {
    return remember(viewModel) {
        MovingCoordinator(
            viewModel = viewModel
        )
    }
}