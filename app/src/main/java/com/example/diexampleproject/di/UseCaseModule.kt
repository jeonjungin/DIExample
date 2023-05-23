package com.example.diexampleproject.di

import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetPokemonUseCase(
        repo: PokemonRepository
    ): GetPokemonUseCase {
        return GetPokemonUseCase(repo)
    }
}