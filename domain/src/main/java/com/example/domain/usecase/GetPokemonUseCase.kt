package com.example.domain.usecase

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow
import com.example.domain.Result

class GetPokemonUseCase(
    private val repo: PokemonRepository
) {
    suspend operator fun invoke(id: String): Result<Pokemon> {
        if (id.isEmpty()) {
            return repo.fetchPokemon("0")
        }
        return repo.fetchPokemon(id)
    }
}