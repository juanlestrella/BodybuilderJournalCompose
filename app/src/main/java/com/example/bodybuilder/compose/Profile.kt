package com.example.bodybuilder.compose

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bodybuilder.viewmodels.ProfileViewModel

@Composable
fun Profile_Screen(){
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
            /**
             * TODO: Change this to BottomNavigation later after creating all the screens
             */
            BottomAppBar(backgroundColor = MaterialTheme.colors.background) {
                Text(text = "Bottom App Bar", color = Color.Red)
            }
        }
    ){ contentPadding ->
        ProfileBodyContent(contentPaddingValues = contentPadding)
    }
}

@Composable
fun ProfileBodyContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
){
    // val focusManager = LocalFocusManager.current
    val context = LocalContext.current as Activity

    val viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()

    var age by rememberSaveable{ mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var bmi by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(contentPaddingValues)
    ){
        TextField(
            value = age,
            onValueChange = {
                if (it.length <= 6){
                    age = it
                }
            },
            label = {Text("Age")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
        TextField(
            value = weight,
            onValueChange = {
                if (it.length <= 6){
                    weight = it
                }
            },
            label = {Text("Weight(kg)")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
        TextField(
            value = height,
            onValueChange = {
                if (it.length <= 5){
                    height = it
                }
            },
            label = {Text("Height(cm)")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = bmi, // change the value to repository's bmiState
            onValueChange = {},
            label = {Text("BMI")},
            readOnly = true
        )
        Button(
            onClick = {
                if (age.isNullOrEmpty() || age.toInt() < 0 || age.toInt() > 80){
                    Toast.makeText(context, "Please enter age between 0 to 80", Toast.LENGTH_SHORT).show()
                } else if (weight.isNullOrEmpty() || weight.toInt() < 40 || weight.toInt() > 160){
                    Toast.makeText(context, "Please enter weight between 40 kg and 160 kg", Toast.LENGTH_SHORT).show()
                } else if (height.isNullOrEmpty() || height.toInt() < 130 || height.toInt() > 230){
                    Toast.makeText(context, "Please enter height between 130 cm and 230 cm", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.insertBmi(age, weight, height)
                    viewModel.getBmi()
                    bmi = viewModel.bmiState.value.bmi.toString()
                }
            }
        ){
            Text(text = "Submit")
        }
    }


}