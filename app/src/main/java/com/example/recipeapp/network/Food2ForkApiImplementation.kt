package com.example.recipeapp.network

import android.util.Log
import com.example.recipeapp.network.Food2ForkDTO.SearchResponseDTO
import com.example.recipeapp.util.NetworkError
import com.example.recipeapp.util.ResponseStatus
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Inject


class Food2ForkApiImplementation @Inject constructor(
    val client:HttpClient,
    val json: Json
):Food2ForkApi {
    private val baseURL = "https://food2fork.ca/api/recipe/"

    private fun List<String>.toQueryString() = when(size){
        0 -> ""
        1-> this[0]
        else-> this.reduce{acc,s-> acc+" "+s}
    }
    private suspend fun<T>  makeHttpRequest(
        url:String,
        serializeFunc: suspend (response:HttpResponse)->T
    ):ResponseStatus<T>{
        Log.d("Debug","Making Http Request!...")

        val httpResponse = client.get(url)
        Log.d("Debug","Http Request Successfull....")

         return when (httpResponse.status){
            HttpStatusCode.OK -> ResponseStatus.Success(serializeFunc(httpResponse))
            else-> ResponseStatus.Failure(NetworkError.HttpError(httpResponse.status))
        }
    }

    override suspend fun getFood2ForkData(
        pageNum:Int,
        queryTerms:List<String>): ResponseStatus<SearchResponseDTO> {
        val url = baseURL + "search/?page=${pageNum}&query=${queryTerms.toQueryString()}"
        Log.d("Debug","Making request to url ${url}")

        return makeHttpRequest(url){response-> response.body()}
    }

    override suspend fun searchFood2ForkData(id: Int): ResponseStatus<SearchResponseDTO> {
        val url = baseURL + "get/?id=${id}"

        return makeHttpRequest(url){response-> response.body()}
    }
}