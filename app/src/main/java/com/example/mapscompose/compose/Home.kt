package com.example.mapscompose.compose

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
 *      0) -> make image zoom out once clicked
 *      -> create an "X" to close the image
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
    // make this clickable and go to more detailed page (navigation)
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
    val isClicked = remember { mutableStateOf(false) }
    val imageModifier = modifier
        .size(150.dp)
        .background(Color.Gray)
        .fillMaxWidth()
        .clickable(
            enabled = true,
            onClick = { // use this to show larger image of clicked image
                Toast.makeText(context, "Clicked Image", Toast.LENGTH_SHORT).show()
                isClicked.value = !isClicked.value
            },
        )
    Surface{
        Column(
            modifier = modifier.padding(all = 4.dp)
        ){
            Card(
                shape = CircleShape,
                elevation = 20.dp
            ){
                Image( // this is causing render problem in Preview but can just refresh and it works
                    painter = painterResource(id = image),
                    contentDescription = description,
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier,
                )
            }
            Text(text = date)
            Text(text = title)
        }
        if (isClicked.value){
            BoxImage(modifier, image)
        }
    }

}

@Composable
fun BoxImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.baseline_smart_toy_24,
    configuration: Configuration = LocalConfiguration.current
){
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    Box(
        modifier = modifier
            .clip(RectangleShape)
            .background(color = MaterialTheme.colors.background)
            .border(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black)
            .size(height = configuration.screenHeightDp.dp, width = configuration.screenWidthDp.dp)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                }
            }
    ){
        Image(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = maxOf(.5f, minOf(3f, scale.value)),
                    scaleY = maxOf(.5f, minOf(3f, scale.value)),
                    rotationZ = rotationState.value
                ),
            contentDescription = "Zoom Image",
            contentScale = ContentScale.Fit,
            painter = painterResource(id = image)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Home_Screen_Preview(){
    MapsComposeTheme {
        Home_Screen()
    }
}
