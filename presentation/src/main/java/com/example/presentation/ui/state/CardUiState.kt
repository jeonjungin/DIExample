package com.example.presentation.ui.state

sealed class CardUiState {
    
    object Empty: CardUiState()

    data class Valid(
        val id : String,
        val name: String,
        val imgUrl: String,
        val hp: HpUiState
    ): CardUiState()
}