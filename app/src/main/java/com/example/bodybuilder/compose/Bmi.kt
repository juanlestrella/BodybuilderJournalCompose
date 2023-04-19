package com.example.bodybuilder.compose

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bodybuilder.ui.theme.Bodybuilder
import com.example.bodybuilder.viewmodels.BmiViewModel

@Composable
fun Bmi_Screen(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Home", color = Color.Red) },
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
        BmiBodyContent(contentPaddingValues = contentPadding)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BmiBodyContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
){
    // val focusManager = LocalFocusManager.current
    val context = LocalContext.current as Activity

    val viewModel: BmiViewModel = hiltViewModel()

    var age by rememberSaveable{ mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }

    val bmi by viewModel.bmiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.padding(contentPaddingValues)
    ){
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        TextFieldHeight(height = height, onTextChange = { height = it}, imeAction = ImeAction.Done)
        TextField(
            value = bmi.toString(),
            onValueChange = {},
            label = { Text(text = "BMI")},
            readOnly = true
        )
        Button(
            onClick = {
                if (age.isEmpty() || age.toInt() < 0 || age.toInt() > 80){
                    Toast.makeText(context, "Please enter age between 0 to 80", Toast.LENGTH_SHORT).show()
                } else if (weight.isEmpty() || weight.toInt() < 40 || weight.toInt() > 160){
                    Toast.makeText(context, "Please enter weight between 40 kg and 160 kg", Toast.LENGTH_SHORT).show()
                } else if (height.isEmpty() || height.toInt() < 130 || height.toInt() > 230){
                    Toast.makeText(context, "Please enter height between 130 cm and 230 cm", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.getBmiFromApi(age, weight, height)
                    //bmi = viewModel.bmiState.value.bmi.toString()
                }
            }
        ){
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Bmi_Screen_Preview(){
    Bodybuilder {
        Bmi_Screen()
    }
}