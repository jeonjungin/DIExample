package com.example.presentation.ui.state

sealed class FeelingUiState {
    abstract val value: Int
    data class Low(
        @androidx.annotation.IntRange(from = 0, to = 100)
        override val value: Int
    ): FeelingUiState()

    data class High(
        @androidx.annotation.IntRange(from = 0, to = 100)
        override val value: Int
    ): FeelingUiState()
}