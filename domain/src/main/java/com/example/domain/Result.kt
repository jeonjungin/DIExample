package com.example.domain

sealed class Result<out T> {
    data class Success<T>(
        val value: T
    ): Result<T>()

    data class GenericError(
        val code: String,
        val msg: String
    ): Result<Nothing>()

    object NetworkError: Result<Nothing>()
}