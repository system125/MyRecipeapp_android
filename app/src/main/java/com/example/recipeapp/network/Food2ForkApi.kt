package com.example.recipeapp.network

import com.example.recipeapp.network.Food2ForkDTO.SearchResponseDTO
import com.example.recipeapp.util.ResponseStatus

interface Food2ForkApi {
    suspend fun getFood2ForkData (
        pageNum:Int,
        queryTerms:List<String>
    ):ResponseStatus<SearchResponseDTO>

    suspend fun searchFood2ForkData (
        id:Int
    ):ResponseStatus<SearchResponseDTO>
}