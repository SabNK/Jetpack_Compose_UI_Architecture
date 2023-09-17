package com.example.uiarchitecture

import android.graphics.Bitmap

data class PlacesState(
    val places: List<Place> = emptyList(),
    val error: String? = null
)

sealed interface PlacesAction {
    class NavigateToButtonClicked(val place: Place) : ParcelActions()
    class FavoritesButtonClicked(val place: Place) : ParcelActions()
}

data class Place(
    val picture: Bitmap,
    val name: String,
    val rating: Int
)

open class ParcelActions(
    val onFavoritesClicked: (Place) -> Unit = {},
    val onNavigateToButtonClicked: (Place) -> Unit = {},
)