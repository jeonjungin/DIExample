package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetPokemonUseCase
import com.example.presentation.ui.state.CardUiState
import com.example.domain.RepoResult
import com.example.domain.model.Pokemon
import com.example.presentation.ui.state.HpUiState
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

    private fun updateById(id: String) {
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

    private fun RepoResult<Pokemon>.toCardUiState() = when (this) {
        is RepoResult.Success -> value.toValidCard()
        is RepoResult.GenericError -> CardUiState.Empty
        is RepoResult.NetworkError -> CardUiState.Empty
    }

    private fun Pokemon.toValidCard(): CardUiState.Valid {
        val hpState = if (hp > 50) {
            HpUiState.High(hp)
        } else {
            HpUiState.Low(hp)
        }

        return CardUiState.Valid(
            id = id,
            name = name,
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
            hp = hpState
        )
    }
}