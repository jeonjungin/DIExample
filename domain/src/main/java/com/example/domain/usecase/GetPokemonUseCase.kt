package com.example.domain.usecase

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.RepoResult

class GetPokemonUseCase(
    private val repo: PokemonRepository
) {
    suspend operator fun invoke(id: String): RepoResult<Pokemon> {
        if (id.isEmpty()) {
            return repo.fetchPokemon("0")
        }
        return repo.fetchPokemon(id)
    }
}