package com.example.recipeapp.presentation.searchScreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.presentation.components.RecipeResultCard
import com.example.recipeapp.presentation.searchScreen.searchscreenstates.LoadingWidget
import com.example.recipeapp.presentation.searchScreen.searchscreenstates.NoWifiWidget
import com.example.recipeapp.presentation.searchScreen.searchscreenstates.SearchEntryNotFoundWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen (searchScreenViewModel:SearchScreenViewModel){
    Column{
        OutlinedTextField(
           value=searchScreenViewModel.searchTextValue,
            onValueChange={newValue->searchScreenViewModel.onSearchValueChanged(newValue)},
            modifier = Modifier.fillMaxWidth(1f),
            singleLine = true
        )

        when(searchScreenViewModel.searchScreenState){
            is SearchScreenState.Loading -> LoadingWidget()
            is SearchScreenState.NetworkError -> Text("Network Error!")
            is SearchScreenState.NoWifi -> NoWifiWidget()
            is SearchScreenState.RecipeNotFound -> SearchEntryNotFoundWidget()
            is SearchScreenState.Success -> RecipeList(
                searchScreenViewModel.searchResultState,
                searchScreenViewModel::onCardClicked
            )
        }





    }
        
}





