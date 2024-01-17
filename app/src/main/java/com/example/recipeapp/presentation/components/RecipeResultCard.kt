package com.example.recipeapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R

//@Composable
//fun RecipeResultCard(
//    recipeCardState: RecipeResultCardState,
//    onClickFunction: (id: Int) -> Unit
//){
//    var visible by remember{ mutableStateOf(true)}
//    AnimatedVisibility(
//        visible =true,
//        enter = fadeIn(
//            animationSpec= tween(durationMillis = 2000),
//            initialAlpha = 0.1f
//        )
//    ) {
//        _recipeResultCard(
//            recipeCardState = recipeCardState,
//            onClickFunction = onClickFunction
//        )
//    }
//
//}

@Composable
fun RecipeResultCard (
   recipeCardState:RecipeResultCardState,
   onClickFunction: (id:Int) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            .clickable { onClickFunction(recipeCardState.id) }
        ,shape= MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ){
       AsyncImage(model =
       ImageRequest.Builder(LocalContext.current)
           .data(recipeCardState.image)
           .placeholder(R.drawable.recipe_default_img)
           .crossfade(true)
           .build(),
           contentDescription = null,
           modifier = Modifier
               .clip(MaterialTheme.shapes.large)
               .padding(8.dp)
               .aspectRatio(3 / 2f)
       )

        Column(modifier = Modifier.padding(
            bottom = 12.dp,
            start = 12.dp,
            end=12.dp
        )){
            Text(
                recipeCardState.recipeName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text (
                recipeCardState.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint=MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text("Favorite: ${recipeCardState.ratings}")

                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Outlined.DateRange,
                    contentDescription = "Date Added",
                    modifier = Modifier,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(" ${recipeCardState.dateAdded}")
            }
        }
    }

}

@Preview
@Composable
fun PreviewRecipeResultCard (){
    RecipeResultCard(
        recipeCardState = RecipeResultCardState(
            image="https://www.image.src",
            ratings = 1,
            dateAdded = "10-12-November",
            description = "Pig Bacon Ipsum",
            recipeName = "Recipe Name",
            id = 0
        )
    ){

    }
}