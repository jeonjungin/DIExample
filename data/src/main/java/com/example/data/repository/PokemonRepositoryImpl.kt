package com.example.data.repository

import com.example.data.remote.toDomain
import com.example.data.service.PokemonService
import com.example.data.toResult
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
): PokemonRepository {
    override suspend fun fetchPokemon(id: String) = pokemonService.fetchPokemonInfo(id).toResult {
        it.toDomain()
    }
}