package com.example.uiarchitecture.feature.addpole

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class AddPoleCoordinator(
    val viewModel: AddPoleViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun onPoleNameEdited() {
        // TODO Handle UI Action
    }

    fun onGeoClick() {
        TODO("Not yet implemented")
    }
}

@Composable
fun rememberAddPoleCoordinator(
    viewModel: AddPoleViewModel = hiltViewModel()
): AddPoleCoordinator {
    return remember(viewModel) {
        com.example.uiarchitecture.feature.addpole.AddPoleCoordinator(
            viewModel = viewModel
        )
    }
}