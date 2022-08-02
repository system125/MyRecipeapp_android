package com.example.recipeapp.Repository

import android.util.Log
import com.example.recipeapp.Repository.model.toRecipeCardState
import com.example.recipeapp.network.Food2ForkApi
import com.example.recipeapp.network.Food2ForkDTO.RecipeResponseDTO
import com.example.recipeapp.presentation.components.RecipeResultCardState
import com.example.recipeapp.util.NetworkError
import com.example.recipeapp.util.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipeRepositoryImplementation @Inject constructor(
    val food2ForkApi: Food2ForkApi
) :RecipeRepository{

    fun mapRecipeResponseToState (resultDTO:RecipeResponseDTO):RecipeResultCardState{
        return RecipeResultCardState(
            image = resultDTO.featured_image,
            recipeName = resultDTO.title,
            dateAdded = resultDTO.date_added,
            description = resultDTO.description,
            ratings = resultDTO.rating,
            id = resultDTO.pk
        )
    }

    override  fun getRecipeStates(
        pageNum: Int,
        queries: List<String>
    ): Flow<ResponseStatus<RecipeResultCardState>> {
        return flow{
            val response = food2ForkApi.getFood2ForkData(pageNum, queryTerms = queries)
            emit(ResponseStatus.Loading())

            when (response){
                is ResponseStatus.Success -> context@ {
                    Log.d("Debug","Succesful Response to repository")
                    val responseList = response.response.results

                    if (responseList.size == 0){
                        emit(ResponseStatus.Failure(NetworkError.QueryNotFound))
                    }else{
                        val recipeResponseFlow = responseList.asFlow()

                        val recipeCardDataFlow = recipeResponseFlow
                            .map{recipeDTO->recipeDTO.toRecipeCardState()}
                            .map{recipeState-> ResponseStatus.Success(recipeState)}
                            .onEach { Log.d("Debug","Sending response data ${it.response}") }
                            .onEach { emit(it)}

                        emitAll(recipeCardDataFlow)
                    }

                }
                is ResponseStatus.Loading ->
                    emit(ResponseStatus.Loading())
                is ResponseStatus.Failure->{
                    emit(ResponseStatus.Failure(response.networkError))
                }
            }
        }
    }
}