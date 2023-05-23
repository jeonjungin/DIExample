package com.example.data

import com.skydoves.sandwich.ApiResponse
import com.example.domain.RepoResult

fun <T, R> ApiResponse<T>.toResult(mapper: (raw: T) -> R) = when (this) {
    is ApiResponse.Success -> {
        RepoResult.Success(mapper(data))
    }
    is ApiResponse.Failure.Error -> {
        RepoResult.GenericError(statusCode.code.toString(), "")
    }
    is ApiResponse.Failure.Exception -> {
        RepoResult.NetworkError
    }
}