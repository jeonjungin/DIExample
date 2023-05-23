package com.example.presentation.ui.state

data class SearchUiState(
    val input: String,
    val onValueChange: (text: String) -> Unit
)