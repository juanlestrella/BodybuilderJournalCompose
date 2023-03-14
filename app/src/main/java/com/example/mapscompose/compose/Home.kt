package com.example.mapscompose.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mapscompose.R
import com.example.mapscompose.ui.theme.MapsComposeTheme


//todo add scrollable and lazy layouts

@Composable
fun Home_Screen() {
    Column(){ // Needs to be Lazy
        Spacer(modifier = Modifier.height(16.dp)) // this helps avoid recomposition of the child composable function
        LocationBar()
        Spacer(modifier = Modifier.height(16.dp))
        LocationBar()
    }
}

@Composable
fun LocationBar(){
    Column(){
        Text(text = "Location Name")
        Row() {// Needs to be Lazy
            ContentCard()
            ContentCard()
            ContentCard()
        }
    }
}

@Composable
fun ContentCard(){
    Card(){
        Column(){
            val imageModifier = Modifier
                .size(150.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Gray)
                .fillMaxWidth()
            Image(
                painter = painterResource(id = R.drawable.baseline_smart_toy_24),
                contentDescription = "temp description",
                contentScale = ContentScale.Fit,
                modifier = imageModifier)
            Text(text = "Title")
            Text(text = "Date")
        }
    }

}

@Preview
@Composable
fun Home_Screen_Preview(){
    MapsComposeTheme {
        Home_Screen()
    }
}
