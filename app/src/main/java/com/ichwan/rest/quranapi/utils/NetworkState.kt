package com.ichwan.rest.quranapi.utils

sealed class NetworkState<out R> private constructor(){

    data class Success<out T>(val data: T): NetworkState<T>()

    data class Error(val message: String): NetworkState<Nothing>()

    object Loading: NetworkState<Nothing>()
}