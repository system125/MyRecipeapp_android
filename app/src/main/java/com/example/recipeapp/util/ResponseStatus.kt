package com.example.recipeapp.util

import io.ktor.http.*

sealed class ResponseStatus<T>{
    class Success <R>(val response:R):ResponseStatus<R> ()
    class Loading <R>:ResponseStatus<R>()
    class Failure <R>(val networkError: Error):ResponseStatus<R> ()
}



sealed class NetworkError(val errorMessage:String):Error(){
    object NoInternetError:NetworkError("Error!! No Internet Available!")
    object QueryNotFound:NetworkError("Error!Not Found)")
    class HttpError(httpErrorStatusCode:HttpStatusCode):NetworkError("Network Error!! Code ${httpErrorStatusCode.value} ${httpErrorStatusCode.description}")
}



