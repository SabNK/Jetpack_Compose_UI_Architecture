package com.example.uiarchitecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class PlacesViewModel: ViewModel() {
    fun dismissError() {
        TODO("Not yet implemented")
    }

    private val _stateFlow: MutableStateFlow<PlacesState> = MutableStateFlow(PlacesState())

    val stateFlow: StateFlow<PlacesState> = _stateFlow.asStateFlow()

    val errorFlow = stateFlow.mapNotNull {it.error}

    fun toggleFavorites() {

    }
}