package com.example.data.remote

import com.example.domain.model.Pokemon
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class PokemonRemote (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: TypeRemote? = null,
    val hp: Int = Random.nextInt(100)
)

data class TypeRemote (
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDetailRemote
)

data class TypeDetailRemote (
    @SerializedName("name")
    val name: String? = null
)

fun PokemonRemote.toDomain() = Pokemon(
    id = id?: "",
    name = name?: "",
    type = type?.type?.name?: "",
    hp = hp
)