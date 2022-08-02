package com.example.recipeapp.presentation.components

data class RecipeResultCardState(
    val id:Int,
    val image:String,
    val dateAdded:String,
    val ratings:Int,
    val description:String,
    val recipeName:String
)
