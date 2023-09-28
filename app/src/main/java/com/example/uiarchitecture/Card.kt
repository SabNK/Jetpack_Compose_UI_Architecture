@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uiarchitecture

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedCardWithTitle(
    content: @Composable ColumnScope.(MutableInteractionSource, () -> Unit) -> Unit,
    title: String? = null,
    modifier: Modifier = Modifier.padding(8.dp),
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var focusOnCard by remember { mutableStateOf(false) }
    val focusOnContent by interactionSource.collectIsFocusedAsState()
    val focused = focusOnCard or focusOnContent
    //ToDo Check and replace with the Theme color
    var color  = if (focused) Color.Magenta else Color.DarkGray
    fun onClickAtContentOrCard() {
        focusManager.clearFocus()
        focusRequester.requestFocus()
    }
    Box( modifier = modifier) {
        OutlinedCard(
            onClick = ::onClickAtContentOrCard,
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusOnCard = it.isFocused }
                .focusable().padding(top = 8.dp),
            shape = RoundedCornerShape(if (focused) 4.dp else 2.dp),
            border = BorderStroke(if (focused) 2.dp else 1.dp, color),
            interactionSource = interactionSource,
        ) { content(interactionSource, ::onClickAtContentOrCard) }
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                //ToDo Check and replace with the Theme background color
                .background(color = Color.White),
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = title.orEmpty(),
                style = typography.labelMedium,
                color = color
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}




/*@Preview()
@Composable
fun CardPreview(){
    OutlinedCardWithTitle(
        content = { modifier ->
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = modifier)
        },
        title = "Title"
    )
}*/



val typography = androidx.compose.material3.Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),)