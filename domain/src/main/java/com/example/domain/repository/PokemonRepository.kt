package com.example.domain.repository

import com.example.domain.Result
import com.example.domain.model.Pokemon

interface PokemonRepository {

    suspend fun fetchPokemon(id: String): Result<Pokemon>
}