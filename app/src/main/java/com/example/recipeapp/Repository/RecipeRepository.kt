package com.example.recipeapp.Repository

import com.example.recipeapp.presentation.components.RecipeResultCardState
import com.example.recipeapp.util.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipeStates (
        pageNum:Int,
        queries:List<String>
    ):Flow<ResponseStatus<RecipeResultCardState>>
}