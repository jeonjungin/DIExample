package com.example.presentation

import com.example.domain.RepoResult
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonUseCase
import com.example.presentation.ui.state.CardUiState
import com.example.presentation.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val repository = mock<PokemonRepository>()
    private val useCase = GetPokemonUseCase(repository)


    @get: Rule
    val testRule = TestMainCoroutinesRule()

    @Before
    fun setup() {
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `초기 상태 테스트`() {
        assertThat(viewModel.cardUiState.value, IsEqual(CardUiState.Empty))
        assertThat(viewModel.inputUiState.value.input, IsEqual(""))
    }

    @Test
    fun `정상 값 설정 테스트`() = runTest {
        // given
        val mockResult = RepoResult.Success(
            value = Pokemon(
                id = "1",
                name = "JungIn",
                type = "Fire",
                hp = 100
            )
        )
        whenever(repository.fetchPokemon("1")).thenReturn(mockResult)

        // when
        viewModel.inputUiState.value.onValueChange.invoke("1")

        // then
        val cardState = viewModel.cardUiState.first()
        assertThat(cardState, IsInstanceOf(CardUiState.Valid::class.java))
    }

    @Test
    fun `비정상 값 설정 테스트`() = runTest {
        // given
        val mockResult = RepoResult.NetworkError
        whenever(repository.fetchPokemon("2")).thenReturn(mockResult)

        // when
        viewModel.inputUiState.value.onValueChange.invoke("2")

        // then
        val cardString = viewModel.cardUiState.first()
        assertThat(cardString, IsInstanceOf(CardUiState.Empty::class.java))
    }
}