package com.example.recipeapp.presentation.searchScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.Repository.RecipeRepository
import com.example.recipeapp.presentation.components.RecipeResultCardState
import com.example.recipeapp.util.NetworkError
import com.example.recipeapp.util.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchRepository: RecipeRepository
):ViewModel () {
    val searchResultState = mutableStateListOf<RecipeResultCardState>()
    var searchTextValue by mutableStateOf("")

    private val searchQueryFlow = MutableSharedFlow<String>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val searchQueryDebounceTime = 2000L
    private var coroutineJob:Job = Job ()

    init{
        searchQueryFlow
            .debounce(searchQueryDebounceTime)
            .onEach { coroutineJob.cancel()}
            .onEach { searchResultState.clear()}
            .filter{it-> it!=""}
            .onEach{searchQuery-> coroutineJob = searchRepository(searchQuery)}
            .launchIn(viewModelScope)
    }

    fun searchRepository(searchQuery: String):Job{
        /*TODO(Launch snackbar event on Error!")*/
        return searchRepository.getRecipeStates(1,listOf(searchQuery))
            .onEach {recipeState->
                when(recipeState){
                    is ResponseStatus.Success -> searchResultState.add(recipeState.response)
                    is ResponseStatus.Loading -> Log.d("Debug","Loading state...")
                    is ResponseStatus.Failure -> {
                        when(recipeState.networkError){
                            is NetworkError.HttpError -> Log.d("Debug","Error! ${recipeState.networkError.errorMessage}")
                            is NetworkError.QueryNotFound -> Log.d("Debug","yQuery Not Found")
                            is NetworkError.NoInternetError -> Log.d("Debug","Error! Network Not Found!")
                        }
                    }
                }
            }.launchIn(viewModelScope)

    }


    fun onSearchValueChanged(newSearchValue:String){
        val re = Regex("[^A-Za-z ]")
        val filteredSearchText = newSearchValue.replace(re,"").lowercase()
        Log.d("Debug","$newSearchValue Filtered to $filteredSearchText")
        searchTextValue= filteredSearchText


        viewModelScope.launch {
            searchQueryFlow.emit(filteredSearchText)
        }
    }

    fun onCardClicked(cardId:Int){
        Log.d("Msg","Clicked card with id ${cardId}")
    }

    sealed class SearchQueryType{
        class InitialSearch (val searchQuery:String):SearchQueryType()
        object NextPage:SearchQueryType()
    }


}