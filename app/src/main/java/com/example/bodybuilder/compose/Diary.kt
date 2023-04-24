package com.example.bodybuilder.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.bodybuilder.ui.theme.Bodybuilder

@Composable
fun DiaryScreen() {
    // can be used to show Snack bar, open/close drawer
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Diary", color = Color.Red) },
                backgroundColor = MaterialTheme.colors.background,
            )
        },
        bottomBar = {
            /**
             * TODO: Change this to BottomNavigation later after creating all the screens
             */
            /**
             * TODO: Change this to BottomNavigation later after creating all the screens
             */
            BottomAppBar(backgroundColor = MaterialTheme.colors.background) {
                Text(text = "Bottom App Bar", color = Color.Red)
            }
        }
    ){ contentPadding ->
        DiaryBodyContent(contentPaddingValues = contentPadding)
    }
}

@Composable
fun DiaryBodyContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
) {
    Column(modifier = modifier.padding(contentPaddingValues)) {
        Button(
            onClick = { /*TODO*/ },
        ) {
            //Image(painter = , contentDescription = )
            Text("Body Mass Index")
        }
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text("Body Fat")
        }
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text("Daily Calorie")
        }
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text("Macros")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Diary_Screen_Preview(){
    Bodybuilder {
        DiaryScreen()
    }
}