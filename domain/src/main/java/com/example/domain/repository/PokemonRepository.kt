package com.example.domain.repository

import com.example.domain.RepoResult
import com.example.domain.model.Pokemon

interface PokemonRepository {

    suspend fun fetchPokemon(id: String): RepoResult<Pokemon>
}