package com.example.recipeapp.network.Food2ForkDTO

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseDTO(
    val cooking_instructions: String? = null,
    val date_added: String,
    val date_updated: String,
    val description: String,
    val featured_image: String,
    val ingredients: List<String>,
    val long_date_added: Int,
    val long_date_updated: Int,
    val pk: Int,
    val publisher: String,
    val rating: Int,
    val source_url: String,
    val title: String
)