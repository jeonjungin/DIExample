package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetPokemonUseCase
import com.example.presentation.ui.state.CardUiState
import com.example.domain.Result
import com.example.domain.model.Pokemon
import com.example.presentation.ui.state.FeelingUiState
import com.example.presentation.ui.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel() {

    private val insertIdEvent = MutableStateFlow<String?>(null)

    private val getPokemonFlow = insertIdEvent.filterNotNull().map { id ->
        getPokemonUseCase(id)
    }

    private val _inputUiState = MutableStateFlow(convertInputUiState())
    val inputUiState = _inputUiState.asStateFlow()

    val cardUiState = getPokemonFlow.mapLatest {
        it.toCardUiState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CardUiState.Empty
    )

    fun updateById(id: String) {
        viewModelScope.launch {
            insertIdEvent.emit(id)
            _inputUiState.update {
                it.copy(input = id)
            }
        }
    }

    private fun convertInputUiState(input: String = "") = SearchUiState(
        input = input,
        onValueChange = {
            updateById(it)
        }
    )

    private fun Result<Pokemon>.toCardUiState() = when (this) {
        is Result.Success -> value.toValidCard()
        is Result.GenericError -> CardUiState.Empty
        is Result.NetworkError -> CardUiState.Empty
    }

    private fun Pokemon.toValidCard(): CardUiState.Valid {
        val feeling = if (feeling > 50) {
            FeelingUiState.High(feeling)
        } else {
            FeelingUiState.Low(feeling)
        }

        return CardUiState.Valid(
            id = id,
            name = name,
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
            feeling = feeling
        )
    }
}