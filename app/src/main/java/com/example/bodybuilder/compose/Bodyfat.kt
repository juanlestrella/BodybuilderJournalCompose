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

@Composable
fun BodyFat_Screen(){
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
        BodyFatContent(contentPaddingValues = contentPadding)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyFatContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
) {
    val context = LocalContext.current as Activity

    var age by rememberSaveable{ mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var neck by rememberSaveable{ mutableStateOf("") }
    var waist by rememberSaveable{ mutableStateOf("") }
    var hip by rememberSaveable{ mutableStateOf("") }

    var genderExpanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("male", "female")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }

    Column(
        modifier = modifier.padding(paddingValues = contentPaddingValues)
    ) {
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        ExposedDropdownMenuBox(
            expanded = genderExpanded,
            onExpandedChange = {
                genderExpanded != genderExpanded
            },
        ) {
            TextField(
                readOnly = true,
                value = selectedGender,
                onValueChange = {},
                label = {Text("Gender")},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = genderExpanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = {
                    genderExpanded = false
                }
            ) {
                genderOptions.forEach{ option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedGender = option
                            genderExpanded = false
                        }
                    ) {
                        Text(text = selectedGender)
                    }
                }
            }
        }
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        TextFieldHeight(height = height, onTextChange = {height = it}, imeAction = ImeAction.Next)
        TextFieldNeck(neck = neck, onTextChange = {neck = it}, imeAction = ImeAction.Next)
        TextFieldWaist(waist = waist, onTextChange = {waist = it}, imeAction = ImeAction.Next)
        TextFieldHip(hip = hip, onTextChange = {hip = it}, imeAction = ImeAction.Done)
        Button(
            onClick = {
                if (age.isEmpty() || age.toInt() < 0 || age.toInt() > 80){
                    Toast.makeText(context, "Please enter age between 0 to 80", Toast.LENGTH_SHORT).show()
                } else if (weight.isEmpty() || weight.toInt() < 40 || weight.toInt() > 160){
                    Toast.makeText(context, "Please enter weight between 40 kg and 160 kg", Toast.LENGTH_SHORT).show()
                } else if (height.isEmpty() || height.toInt() < 130 || height.toInt() > 230){
                    Toast.makeText(context, "Please enter height between 130 cm and 230 cm", Toast.LENGTH_SHORT).show()
                } else if (neck.isEmpty() || neck.toInt() < 20 || neck.toInt() > 80){
                    Toast.makeText(context, "Please enter neck measurement between 20 cm and 80 cm", Toast.LENGTH_SHORT).show()
                }else if (waist.isEmpty() || waist.toInt() < 40  || waist.toInt() > 130){
                    Toast.makeText(context, "Please enter waist measurement between 40  cm and 130 cm", Toast.LENGTH_SHORT).show()
                }else if (hip.isEmpty() || hip.toInt() < 40 || hip.toInt() > 130){
                    Toast.makeText(context, "Please enter hip measurement between 40 cm and 130 cm", Toast.LENGTH_SHORT).show()
                }else{
                    //viewModel.getBmiFromApi(age, weight, height)
                    Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show()
                }
            }
        ){
            Text(text = "Submit")
        }
    }
}
