package com.example.recipeapp.presentation.searchScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.recipeapp.presentation.components.RecipeResultCard
import com.example.recipeapp.presentation.components.RecipeResultCardState

@Composable
fun RecipeList(
    searchScreenStates: SnapshotStateList<RecipeResultCardState>,
    onCardClicked: (cardId:Int) -> Unit
){
    LazyColumn {
        items(searchScreenStates) { item ->
            RecipeResultCard(
                recipeCardState = item,
                onClickFunction = onCardClicked
            )
        }
    }
}