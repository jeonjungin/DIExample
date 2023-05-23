package com.example.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.ui.composable.PokemonCard
import com.example.presentation.ui.composable.PokemonSearchField
import com.example.presentation.ui.state.CardUiState
import com.example.presentation.ui.state.SearchUiState
import com.example.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    @Composable
    private fun Screen() {
        val inputUiState: SearchUiState by viewModel.inputUiState.collectAsStateWithLifecycle()
        val cardUiState: CardUiState by viewModel.cardUiState.collectAsStateWithLifecycle()

        Column {
            PokemonSearchField(
                state = inputUiState
            )
            PokemonCard(
                state = cardUiState
            )
        }
    }

}