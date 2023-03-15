package com.example.mapscompose.compose

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.sp
import com.example.mapscompose.R
import com.example.mapscompose.ui.theme.MapsComposeTheme


/**
 * TODO:
 * 1) Apply Card Effects
 * 2) Make each Card clickable
 * 3) Implement Scaffold
 * 4) Implement network
 * 5) Implement entities and database
 * 6) Implement ViewModels
 * 7) Implement repositories
 * 8) Implement navigation
 */

@Composable
fun Home_Screen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        userScrollEnabled = true
    ){ // Needs to be Lazy
        items(10){
            Spacer(modifier = modifier.height(16.dp)) // this helps avoid recomposition of the child composable function
            LocationBar()
        }
    }
}

@Composable
fun LocationBar(
    modifier: Modifier = Modifier,
    location: String = "Location Name"
){
    Column{
        Text(text = location, modifier = modifier.padding(4.dp), fontSize = 32.sp)
        Card(
            //todo: apply effects
        ){
            LazyRow(
                userScrollEnabled = true
            ) {
                items(10){
                    ContentCard()
                }
            }
        }
    }
}

@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.baseline_smart_toy_24,
    description: String = "temp description",
    title: String = "Caption",
    date: String = "Date"
){
    Column(
        modifier = modifier.padding(horizontal = 4.dp)
    ){
        val imageModifier = modifier
            .size(150.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.Gray)
            .fillMaxWidth()
        Image(
            painter = painterResource(id = image),
            contentDescription = description,
            contentScale = ContentScale.Fit,
            modifier = imageModifier)
        Text(text = date)
        Text(text = title)
    }
}

@Preview
@Composable
fun Home_Screen_Preview(){
    MapsComposeTheme {
        Home_Screen()
    }
}
