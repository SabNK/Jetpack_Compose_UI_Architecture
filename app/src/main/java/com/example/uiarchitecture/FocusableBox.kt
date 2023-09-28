package com.example.uiarchitecture

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

private const val TAG = "FocusableBox"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusableBox(){

    val focusRequester = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current
    //to caller
    var text by remember { mutableStateOf("") }
    var ok by remember { mutableStateOf(false) }
    fun onAction() {
        focusManager.clearFocus()
        focusRequester.requestFocus()
    }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    var focusedOutlined by remember { mutableStateOf(false) }
    val focusedContent by interactionSource.collectIsFocusedAsState()
    val focused = focusedOutlined or focusedContent
    Log.d(TAG, "FocusableBox: focusedOutlined $focusedOutlined")
    Log.d(TAG, "FocusableBox: focused $focused")
    val color  = if (focused) Color.Magenta else Color.DarkGray

    Column {
        Box {
            OutlinedCard(
                onClick = { focusRequester.requestFocus() },
                modifier = Modifier
                    //.size(100.dp)
                    //.clickable { focusRequester.requestFocus() }
                    .border(2.dp, color)
                    // The focusRequester should be added BEFORE the focusable.
                    .focusRequester(focusRequester)
                    // The onFocusChanged should be added BEFORE the focusable that is being observed.
                    .onFocusChanged {
                        if (it.isFocused) {
                            //color = Color.Green
                            focusedOutlined = true
                        } else {
                            //color = Color.Black
                            focusedOutlined = false
                        }
                    }
                    .focusable(),
                shape = RoundedCornerShape(4.dp),
                interactionSource = interactionSource
            ) {
                Column {
                    Checkbox(
                        checked = ok,
                        onCheckedChange = {
                            ok = it

                            onAction()
                        },
                        //interactionSource = interactionSource
                    )
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        interactionSource = interactionSource,
                    )
                    Button(
                        onClick = {onAction()},
                        //interactionSource = interactionSource,
                    )
                    {
                        Text("Click me")
                    }
                }
            }
        }
        Button(
            onClick = { focusManager.clearFocus() },

            ) { Icon(Icons.Default.Clear, null) }
        Button(
            onClick = {
                focusManager.clearFocus()
                focusRequester.requestFocus()
            },

            ) { Icon(Icons.Default.Done, null) }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            //interactionSource = interactionSource,
        )
    }

}

@Composable
fun InteractionSource.collectIsFocusedAsState(outlinedFocused: Boolean): State<Boolean> {
    val isFocused = remember { mutableStateOf(outlinedFocused) }
    LaunchedEffect(this) {
        val focusInteractions = mutableListOf<FocusInteraction.Focus>()
        interactions.collect { interaction ->
            when (interaction) {
                is FocusInteraction.Focus -> focusInteractions.add(interaction)
                is FocusInteraction.Unfocus -> focusInteractions.remove(interaction.focus)
            }
            isFocused.value = focusInteractions.isNotEmpty() || outlinedFocused
            Log.d(TAG, "collectIsFocusedAsState: We were here")
        }
    }
    return isFocused
}