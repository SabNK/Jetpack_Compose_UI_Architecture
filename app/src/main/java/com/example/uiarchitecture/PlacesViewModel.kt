package com.example.uiarchitecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlacesViewModel: ViewModel() {
    private val _stateFlow: MutableStateFlow<PlacesState> = MutableStateFlow(PlacesState())

    val stateFlow: StateFlow<PlacesState> = _stateFlow.asStateFlow()
}