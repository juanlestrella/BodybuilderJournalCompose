package com.example.bodybuilder.compose

import android.content.Context
import android.graphics.Bitmap
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
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
    viewModel.getAllImagesFromDatabase()
    val allImages = viewModel.allImages.collectAsStateWithLifecycle()
    Log.i("IMAGES HOME", allImages.toString())

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = true
    ){
        items(allImages.value.size){ index ->
            Spacer(modifier = modifier.height(8.dp)) // this helps avoid recomposition of the child composable function
            HomeContentFolder(images = allImages.value[index])//, viewModel = viewModel)
        }
    }
}

/**
 * A content folder which includes a Date and LazyRow of ContentCard
 */
@Composable
fun HomeContentFolder(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    images: ImagesEntity,
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
            Text(text = images.Date.ifEmpty { "Date" }, modifier = modifier.padding(4.dp), fontSize = 28.sp)
            LazyRow(
                userScrollEnabled = true
            ) {
                items(images.listImages.size) { index ->
                    //Log.i("IMAGES ${images.imagesString.size}", images.imagesString[index])
                    ContentCard(image = images.listImages[index])
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
    //@DrawableRes imageHolder: Int = R.drawable.baseline_smart_toy_24,
    description: String = "temp description",
    bodyPart: String = "Body part",
    image: String,
    context: Context = LocalContext.current
){
    val imageModifier = modifier
        .size(150.dp)
        .background(Color.Gray)
        .fillMaxWidth()
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
                    painter = rememberAsyncImagePainter(Uri.parse(image)), // this is causing problem because ones the app is restart the image disappears
                    contentDescription = description,
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                )
//                AsyncImage(
//                    model = Uri.parse(image),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                )
            }
            Text(text = bodyPart)
        }
    }
}

/** ----------PREVIEWS---------- **/
@Preview(showBackground = true)
@Composable
fun Home_Screen_Preview(){
    Bodybuilder {
        HomeScreen(Modifier.padding(bottom = 4.dp))
    }
}
