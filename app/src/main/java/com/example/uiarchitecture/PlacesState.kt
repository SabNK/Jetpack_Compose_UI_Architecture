package com.example.uiarchitecture

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val TAG = "PlacesState"
data class PlacesState(
    val places: List<Place> = emptyList(),
    val error: String? = null
)

//sealed interface PlacesAction {
//    class NavigateToButtonClicked(val place: Place) : ParcelActions()
//    class FavoritesButtonClicked(val place: Place) : ParcelActions()
//}

data class Place(
    val picture: Bitmap,
    val name: String,
    val rating: Int
)

data class PlacesActions(
    val onFavoritesClicked: (Place) -> Unit = {},
    val onNavigateToButtonClicked: (Place) -> Unit = {},
)

class PlacesCoordinator(
    val viewModel: PlacesViewModel,
    val navController: NavController,
    val context: Context,
    private val scope: CoroutineScope
) {

    val stateFlow = viewModel.stateFlow
    val errorFlow = viewModel.errorFlow

    init {
        // now we can observe our state and react to it
        errorFlow
            .onEach { error ->
                context.toast(error)
                viewModel.dismissError()
            }.launchIn(scope)
    }

    // and handle actions
    fun navigateToRoutePlanner() {
        Log.d(TAG, "navigateToRoutePlanner: ")
    }

}

private fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
}