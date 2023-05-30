package com.example.moviecataloguejetpackpro.data.source.remote.response

sealed class Result<out T> {
    data class Success<T>(val responseList: List<T>) : Result<T>()
    object Failure : Result<Nothing>()
}
