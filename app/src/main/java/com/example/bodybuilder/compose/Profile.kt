package com.example.bodybuilder.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.example.bodybuilder.viewmodels.ProfileViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * TODO: Connect this to its viewmodel then repository then retrofit
 */
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
    contentPaddingValues: PaddingValues,
    profileViewModel: ProfileViewModel = viewModel()
){
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    // how to connect all three with viewmodel's state counterparts
    // or do i even want to connect the weight and height? is it only the bmi i need to connect?
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var bmi by rememberSaveable { mutableStateOf("") }

    Column(

    ){
        TextField(
            value = weight,
            onValueChange = {
                if (it.length <= 6){
                    weight = it
                }
            },
            label = {Text("Weight")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    if(weight.isNullOrEmpty()){
                        Toast.makeText(context, "Please enter weight", Toast.LENGTH_SHORT).show()
                    }else if (height.isNullOrEmpty()){
                        focusManager.moveFocus(FocusDirection.Down)
                    }else{
                        //Toast.makeText(context, weight, Toast.LENGTH_SHORT).show()
                        profileViewModel.getBMI()
                        bmi = profileViewModel.bmiState.value.toString()
                    }
                }
            )
        )
        TextField(
            value = height,
            onValueChange = {
                if (it.length <= 5){
                    height = it
                }
            },
            label = {Text("Height (Inch)")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    if(height.isNullOrEmpty()){
                        Toast.makeText(context, "Please enter height", Toast.LENGTH_SHORT).show()
                    }else if (weight.isNullOrEmpty()){
                        focusManager.moveFocus(FocusDirection.Up)
                    }else{
                        //Toast.makeText(context, height, Toast.LENGTH_SHORT).show()
                        profileViewModel.getBMI()
                        bmi = profileViewModel.bmiState.value.toString()
                    }
                }
            )
        )
        TextField(
            value = bmi, // change the value to repository's bmiState
            onValueChange = {},
            label = {Text("BMI")},
            readOnly = true
        )
    }


}