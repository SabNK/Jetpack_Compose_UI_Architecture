package com.example.uiarchitecture.feature.moving.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Moves(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "Moves")
    }
}

@Preview(name = "Moves", device = "spec:width=392.7dp,height=850.9dp,dpi=440")
@Composable
private fun PreviewMoves() {
    Moves()
}