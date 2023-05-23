package com.example.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.ui.state.CardUiState
import com.example.presentation.ui.state.HpUiState

@Composable
fun PokemonCard(
    state: CardUiState
) {

    val contentColor = getCardColor(state)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = contentColor
        ),
        modifier = Modifier
            .padding(
                all = 10.dp
            )
            .fillMaxSize(),

    ) {
        PokemonCardContent(state)
    }
}

@Composable
private fun getCardColor(state: CardUiState) = when (state) {
    is CardUiState.Empty -> colorResource(id = R.color.gray)
    is CardUiState.Valid -> {
        when (state.hp) {
            is HpUiState.Low -> colorResource(id = R.color.blue)
            is HpUiState.High -> colorResource(id = R.color.red)
        }
    }
}

@Composable
private fun PokemonCardContent(state: CardUiState) {
    when (state) {
        is CardUiState.Empty -> {
            EmptyPokemonContent()
        }
        is CardUiState.Valid -> {
            ValidPokemonContent(state = state)
        }
    }
}

@Composable
private fun EmptyPokemonContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Empty",
            modifier = Modifier
        )
    }
}

@Composable
private fun ValidPokemonContent(state: CardUiState.Valid) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = state.imgUrl,
            contentScale = ContentScale.Fit,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .padding(top = 5.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(text = "Id: ${state.id}")
        Text(text = "Name: ${state.name}")
        Text(text = "HP: ${state.hp.value}")
    }
}