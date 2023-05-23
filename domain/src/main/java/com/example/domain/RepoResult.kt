package com.example.domain

sealed class RepoResult<out T> {
    data class Success<T>(
        val value: T
    ): RepoResult<T>()

    data class GenericError(
        val code: String,
        val msg: String
    ): RepoResult<Nothing>()

    object NetworkError: RepoResult<Nothing>()
}