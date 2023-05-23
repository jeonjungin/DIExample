package com.example.presentation.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.state.SearchUiState


@Composable
fun PokemonSearchField(
    state: SearchUiState
) {
    BasicTextField(
        value = state.input,
        singleLine = true,
        onValueChange = {
            state.onValueChange(it)
        },
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 3.dp,
                color = colorResource(id = R.color.gray),
                shape = RoundedCornerShape(10.dp)
            ),
        decorationBox = {
            Row(
               modifier = Modifier
                   .padding(5.dp)
                   .fillMaxWidth()
                   .background(
                       color = Color.White,
                       shape = RoundedCornerShape(10.dp)
                   ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                it()
            }

        }
    )
}