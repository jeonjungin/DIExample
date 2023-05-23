package com.example.data.service

import com.example.data.remote.PokemonRemote
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}")
    suspend fun fetchPokemonInfo(
        @Path("id") id: String
    ): ApiResponse<PokemonRemote>
}