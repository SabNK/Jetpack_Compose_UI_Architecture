@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.uiarchitecture

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "Card"
@Composable
fun OutlinedCardWithTitle(
    content: @Composable ColumnScope.(Modifier) -> Unit,
    contentCount: Int =0,
    title: String? = null,
    modifier: Modifier = Modifier.padding(16.dp),
) {
    var count by remember { mutableStateOf(contentCount) }
    var color = if (count < contentCount) Color.Magenta else Color.DarkGray
    val modifierWithFocus = modifier.onFocusChanged {
        if (it.isFocused) {
            count--
            Log.d(TAG, "OutlinedCardWithTitle: count--: $count")
            
        }
        else {
            count++
            Log.d(TAG, "OutlinedCardWithTitle: count++: $count")
        }
    }
    Box {
        OutlinedCard(
            modifier = modifier.padding(top = 8.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(2.dp, color)
        ) {
            content(modifierWithFocus)
        }
        Row(
            modifier = Modifier
                .padding(start = 10.dp)


                //ToDo Check and replace with the Theme background color
                .background(color = Color.White),
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = title?.let { it } ?: "",
                style = typography.labelMedium

            )
            Spacer(modifier = Modifier.width(5.dp))
        } //Text( "kuku")
    }
}

@Preview(showSystemUi = true)
@Composable
fun CardPreview(){
    OutlinedCardWithTitle(
        content = { modifier ->
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = modifier)
        }
    )
}



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