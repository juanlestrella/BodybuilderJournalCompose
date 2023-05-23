package com.example.bodybuilder.compose

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.bodybuilder.R
import com.example.bodybuilder.models.ImagesEntity
import com.example.bodybuilder.ui.theme.Bodybuilder
import com.example.bodybuilder.viewmodels.HomeViewModel


/**
 * TODO:
 * 0) Apply Card Effects
 * 0) Make each Folder clickable
 *      0) navigate to another screen
 * 0) Implement Scaffold
 *      0) switch bottomBar to BottomNavigation
 *      https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#bottomnavigation
 * 0) Implement network
 * 0) Implement entities and database
 * 0) Implement ViewModels
 * 0) Implement repositories
 * 0) Implement navigation
 * 1) Implement an Add Screen and add it to BottomNavigation
 */

@Composable
fun HomeScreen(
    modifier: Modifier
) {
    HomeBodyContent(modifier = modifier)
}

/**
 * A LazyColumn of content folder from Room DB
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeBodyContent(
    modifier: Modifier,
){
    val viewModel: HomeViewModel = hiltViewModel()
    /**
     * TODO: add a folder holder
     **/
    viewModel.getAllImagesFromDatabase()
    val allImages = viewModel.allImages.collectAsStateWithLifecycle()
    val imageSize = allImages.value.size
    //Log.i("IMAGES HOME", allImages.toString())

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = true
    ){
        items(allImages.value.size){ index -> // Use Room table to determine the size of the LazyColumn ---->> Outter list of viewModel.allImages
            Spacer(modifier = modifier.height(8.dp)) // this helps avoid recomposition of the child composable function
            HomeContentFolder(images = allImages.value[index], viewModel = viewModel)
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
    images: ImagesEntity,
    viewModel: HomeViewModel = hiltViewModel()
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
                items(images.imagesString.size) { index -> // Size is determine by how many pictures the user added ----> Inner list of viewModel.allImages
                    ContentCard(image = images.imagesString[index])
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
    @DrawableRes imageHolder: Int = R.drawable.baseline_smart_toy_24,
    description: String = "temp description",
    bodyPart: String = "Body part",
    image: String
//    context: Context = LocalContext.current
){
//    val isClicked = remember { mutableStateOf(false) }
    val imageModifier = modifier
        .size(150.dp)
        .background(Color.Gray)
        .fillMaxWidth()
//        .clickable(
//            enabled = true,
//            onClick = { // use this to show larger image of clicked image
//                Toast.makeText(context, "Clicked Image", Toast.LENGTH_SHORT).show()
//                isClicked.value = !isClicked.value
//            },
//        )
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
                Image(
                    // this is causing render problem in Preview but can just refresh and it works
                    painter = rememberAsyncImagePainter(model = Uri.parse(image)), //painterResource(id = Uri.parse(image)),
                    contentDescription = description,
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier,
                )
            }
            Text(text = bodyPart)
        }
//        if (isClicked.value){
//            BoxImage(modifier, image)
//        }
    }
}

/**
 * This can be in another file and use navigation
 * Used to enlarge the clicked image
 */
//@Composable
//fun BoxImage(
//    modifier: Modifier = Modifier,
//    @DrawableRes image: Int = R.drawable.baseline_smart_toy_24,
//    configuration: Configuration = LocalConfiguration.current
//){
//    val scale = remember { mutableStateOf(1f) }
//    val rotationState = remember { mutableStateOf(1f) }
//    Box(
//        modifier = modifier
//            .clip(RectangleShape)
//            .background(color = MaterialTheme.colors.background)
//            .border(1.dp, if (isSystemInDarkTheme()) Color.White else Color.Black)
//            .size(height = configuration.screenHeightDp.dp, width = configuration.screenWidthDp.dp)
//            .pointerInput(Unit) {
//                detectTransformGestures { _, _, zoom, rotation ->
//                    scale.value *= zoom
//                    rotationState.value += rotation
//                }
//            }
//    ){
//        Image(
//            modifier = modifier
//                .align(Alignment.Center)
//                .fillMaxSize()
//                .graphicsLayer(
//                    scaleX = maxOf(.5f, minOf(3f, scale.value)),
//                    scaleY = maxOf(.5f, minOf(3f, scale.value)),
//                    rotationZ = rotationState.value
//                ),
//            contentDescription = "Zoom Image",
//            contentScale = ContentScale.Fit,
//            painter = painterResource(id = image)
//        )
//    }
//}
/** ----------PREVIEWS---------- **/
@Preview(showBackground = true)
@Composable
fun Home_Screen_Preview(){
    Bodybuilder {
        HomeScreen(Modifier.padding(bottom = 4.dp))
    }
}
