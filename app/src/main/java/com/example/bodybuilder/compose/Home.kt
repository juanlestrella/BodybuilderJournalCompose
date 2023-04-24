package com.example.bodybuilder.compose

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
import androidx.compose.material.*
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
import com.example.bodybuilder.R
import com.example.bodybuilder.ui.theme.Bodybuilder


/**
 * TODO:
 * 0) Apply Card Effects
 * 0) Make each Card clickable
 *      0) -> make image zoom out once clicked
 * 0) Implement Scaffold
 *      3.a) switch bottomBar to BottomNavigation
 *      https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#bottomnavigation
 * 4) Implement network
 * 5) Implement entities and database
 * 6) Implement ViewModels
 * 7) Implement repositories
 * 8) Implement navigation
 */

@Composable
fun HomeScreen() {
    // can be used to show Snack bar, open/close drawer
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text("Home", color = Color.Red)},
                backgroundColor = MaterialTheme.colors.background,
            )
        },
        bottomBar = {
            /**
             * TODO: Change this to BottomNavigation later after creating all the screens
             */
            BottomAppBar(backgroundColor = MaterialTheme.colors.background) {
                Text(text = "Bottom App Bar", color = Color.Red)
            }
        }
    ){ contentPadding ->
        BodyContent(contentPaddingValues = contentPadding)
    }
}

/**
 * A LazyColumn of content folder from Room DB
 */
@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
){
    LazyColumn(
        modifier = modifier.padding(contentPaddingValues),
        userScrollEnabled = true
    ){
        items(10){ // Use Room table to determine the size of the LazyColumn
            Spacer(modifier = modifier.height(12.dp)) // this helps avoid recomposition of the child composable function
            HomeContentFolder()
        }
    }
}

/**
 * A content folder which includes a Date and LazyRow of ContentCard
 */
@Composable
fun HomeContentFolder(
    modifier: Modifier = Modifier,
    location: String = "Date",
    context: Context = LocalContext.current,
){
    Card(
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black),
        modifier = modifier
            .clickable(
                onClick = {
                    // navigate to detailed profile
                    Toast.makeText(context, "Clicked Card", Toast.LENGTH_SHORT).show()
                }
            )
    ) {
        Column {
            Text(text = location, modifier = modifier.padding(4.dp), fontSize = 28.sp)
            LazyRow(
                userScrollEnabled = true
            ) {
                items(10) {// Size is determine by how many pictures the user added
                    ContentCard()
                }
            }
        }
    }
}

/**
 * A content card that includes an Image and Title
 */
@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.baseline_smart_toy_24,
    description: String = "temp description",
    bodyPart: String = "Body part",
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
                Toast
                    .makeText(context, "Clicked Image", Toast.LENGTH_SHORT)
                    .show()
                isClicked.value = !isClicked.value
            },
        )
    Surface{
        Column(
            modifier = modifier
                .padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Text(text = bodyPart)
        }
        if (isClicked.value){
            BoxImage(modifier, image)
        }
    }
}

/**
 * This can be in another file and use navigation
 * Used to enlarge the clicked image
 */
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
            .border(1.dp, if (isSystemInDarkTheme()) Color.White else Color.Black)
            .size(height = configuration.screenHeightDp.dp, width = configuration.screenWidthDp.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, rotation ->
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
/** ----------PREVIEWS---------- **/
@Preview(showBackground = true)
@Composable
fun Home_Screen_Preview(){
    Bodybuilder {
        HomeScreen()
    }
}
