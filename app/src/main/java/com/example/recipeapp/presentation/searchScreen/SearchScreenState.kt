package com.example.recipeapp.presentation.searchScreen

sealed class SearchScreenState{
    object Success:SearchScreenState()
    object RecipeNotFound:SearchScreenState()
    class NetworkError(val ErrorMessage:String):SearchScreenState()
    object Loading:SearchScreenState()
    object NoWifi:SearchScreenState()
}
