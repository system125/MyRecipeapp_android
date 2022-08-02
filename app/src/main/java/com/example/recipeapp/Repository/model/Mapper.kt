package com.example.recipeapp.Repository.model

import com.example.recipeapp.network.Food2ForkDTO.RecipeResponseDTO
import com.example.recipeapp.presentation.components.RecipeResultCardState

fun RecipeResponseDTO.toRecipeCardState():RecipeResultCardState{
    return RecipeResultCardState(
        id =  pk,
        recipeName = title,
        ratings = rating,
        description = description,
        dateAdded = date_added,
        image = featured_image
    )
}