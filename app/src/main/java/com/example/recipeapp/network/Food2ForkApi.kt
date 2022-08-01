package com.example.recipeapp.network

import android.net.Network
import com.example.recipeapp.network.Food2ForkDTO.SearchResponseDTO
import com.example.recipeapp.util.NetworkResponse

interface Food2ForkApi {
    suspend fun getFood2ForkData (
        pageNum:Int,
        queryTerms:List<String>
    ):NetworkResponse<SearchResponseDTO>
    suspend fun searchFood2ForkData (
        id:Int
    ):NetworkResponse<SearchResponseDTO>
}