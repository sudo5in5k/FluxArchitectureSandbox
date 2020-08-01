package com.example.githubbrowserwithflux.util

sealed class Result<out T> {

    data class Success<out T>(val value: T?): Result<T>()
    data class Failure(val msg: String): Result<Nothing>()
}