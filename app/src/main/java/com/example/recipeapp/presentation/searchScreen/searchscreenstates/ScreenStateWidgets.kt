package com.example.recipeapp.presentation.searchScreen.searchscreenstates

import android.graphics.Bitmap
import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.contentValuesOf
import coil.ImageLoader


@Composable
private fun ScreenStateWidget (content:@Composable () -> Unit){
    Column(
        modifier=Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

    }
}

@Composable
fun NoWifiWidget (){
    ScreenStateWidget {


        Text(
            "You are Offline!",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun NetworkErrorWidget(errorMessage:String){
    ScreenStateWidget {

        Text(
            "Network Error: $errorMessage",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun SearchEntryNotFoundWidget(){
    ScreenStateWidget {

        Text(
            text = "Search text Not Found!!",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun LoadingWidget(){
    ScreenStateWidget {

        Text(
            text = "Loading data.. Please Wait",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}