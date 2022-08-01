package com.example.recipeapp.network

import android.util.Log
import com.example.recipeapp.network.Food2ForkDTO.SearchResponseDTO
import com.example.recipeapp.util.NetworkResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class Food2ForkApiImplementation(
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
    ):NetworkResponse<T>{
        Log.d("Debug","Making Http Request!...")

        val httpResponse = client.get(url)
        Log.d("Debug","Http Request Successfull....")

         return when (httpResponse.status){
            HttpStatusCode.OK -> NetworkResponse.SuccessfulResponse(serializeFunc(httpResponse))
            else-> NetworkResponse.ResponseFailed(httpResponse.status.description)
        }
    }

    override suspend fun getFood2ForkData(
        pageNum:Int,
        queryTerms:List<String>): NetworkResponse<SearchResponseDTO> {
        val url = baseURL + "search/?page=${pageNum}&query=${queryTerms.toQueryString()}"

        return makeHttpRequest(url){response-> response.body()}
    }

    override suspend fun searchFood2ForkData(id: Int): NetworkResponse<SearchResponseDTO> {
        val url = baseURL + "get/?id=${id}"

        return makeHttpRequest(url){response-> response.body()}
    }
}