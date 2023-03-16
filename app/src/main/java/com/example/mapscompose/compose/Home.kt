package com.example.mapscompose.compose

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapscompose.R
import com.example.mapscompose.ui.theme.MapsComposeTheme


/**
 * TODO:
 * 0) Apply Card Effects
 * 0) Make each Card clickable
 *      -> make card zoom out once clicked
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
            Spacer(modifier = modifier.height(12.dp)) // this helps avoid recomposition of the child composable function
            LocationCard()
        }
    }
}

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    location: String = "Location Name"
){
    Card(
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black)
    ) {
        Column {
            Text(text = location, modifier = modifier.padding(4.dp), fontSize = 28.sp)
            LazyRow(
                userScrollEnabled = true
            ) {
                items(10) {
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
    date: String = "Date",
    context: Context = LocalContext.current
){
    val imageModifier = modifier
        .size(150.dp)
        .background(Color.Gray)
        .clickable( // use this to show larger image of clicked image
            enabled = true,
            onClick = { Toast.makeText(context, "Clicked Image", Toast.LENGTH_SHORT).show()},
            /**
             * use box layout to zoom out?
             * https://appdevnotes.com/box-layout-in-jetpack-compose/
             * https://stackoverflow.com/questions/74501531/how-to-maximize-image-on-button-click-in-jetpack-compose
             */

        )
        .fillMaxWidth()

    Column(
        modifier = modifier.padding(all = 4.dp)
    ){
        Card(
            shape = CircleShape,
            elevation = 20.dp
            ){
            Image( // this is causing render problem in Preview but can just refresh and works
                painter = painterResource(id = image),
                contentDescription = description,
                contentScale = ContentScale.Fit,
                modifier = imageModifier,
                )
        }
        Text(text = date)
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun Home_Screen_Preview(){
    MapsComposeTheme {
        Home_Screen()
    }
}
