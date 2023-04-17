package com.example.bodybuilder.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

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
        TextFieldHeight(height = height, onTextChange = { height = it}, imeAction = ImeAction.Next)
        TextField(
            value = neck,
            onValueChange = {}
        )
        TextField(
            value = waist,
            onValueChange = {}
        )
        TextField(
            value = hip,
            onValueChange = {}
        )
    }
}
