package com.example.data

import com.skydoves.sandwich.ApiResponse
import com.example.domain.Result

fun <T, R> ApiResponse<T>.toResult(mapper: (raw: T) -> R) = when (this) {
    is ApiResponse.Success -> {
        Result.Success(mapper(data))
    }
    is ApiResponse.Failure.Error -> {
        Result.GenericError(statusCode.code.toString(), "")
    }
    is ApiResponse.Failure.Exception -> {
        Result.NetworkError
    }
}