package com.example.recipeapp.di

import com.example.recipeapp.Repository.RecipeRepository
import com.example.recipeapp.Repository.RecipeRepositoryImplementation
import com.example.recipeapp.network.Food2ForkApi
import com.example.recipeapp.network.Food2ForkApiImplementation
import com.example.recipeapp.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
abstract class DIModuleBinder{
    @Binds
    abstract fun food2ForkApiProvider(food2ForkApiImplementation: Food2ForkApiImplementation):Food2ForkApi
    @Binds
    abstract fun recipeRepositoryProvider(repositoryImplementation: RecipeRepositoryImplementation):RecipeRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DIModuleProvider{
    @Provides
    fun jsonProvider ():Json = Json{
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    fun provideHttpClient(jsonSerializer: Json):HttpClient = HttpClient(CIO){
        install(ContentNegotiation){
            json(jsonSerializer)
        }
        defaultRequest {
            header("Authorization","Token ${Constants.AUTH_TOKEN}")
        }
        install(Logging)
    }

}
