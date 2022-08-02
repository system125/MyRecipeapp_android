package com.example.recipeapp.network.Food2ForkDTO

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDTO(
    val count:Int,
    val next:String = "",
    val previous:String = "",
    val results:List<RecipeResponseDTO>
)