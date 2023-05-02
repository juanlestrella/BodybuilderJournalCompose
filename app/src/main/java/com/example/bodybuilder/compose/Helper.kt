package com.example.bodybuilder.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldAge(age:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = age,
        onValueChange = {
            if (it.length <= 6){
                onTextChange(it)
            }
        },
        label = { Text("Age") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}

@Composable
fun TextFieldWeight(weight:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = weight,
        onValueChange = {
            if (it.length <= 6){
                onTextChange(it)
            }
        },
        label = {Text("Weight(kg)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}

@Composable
fun TextFieldHeight(height:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = height,
        onValueChange = {
            if (it.length <= 5){
                onTextChange(it)
            }
        },
        label = {Text("Height(cm)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}

@Composable
fun TextFieldNeck(neck:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = neck,
        onValueChange = {
            if (it.length <= 5){
                onTextChange(it)
            }
        },
        label = {Text("Neck(cm)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}
@Composable
fun TextFieldWaist(waist:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = waist,
        onValueChange = {
            if (it.length <= 5){
                onTextChange(it)
            }
        },
        label = {Text("Waist(cm)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}
@Composable
fun TextFieldHip(hip:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = hip,
        onValueChange = {
            if (it.length <= 5){
                onTextChange(it)
            }
        },
        label = {Text("Hip(cm)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Spinner(options: List<String>, onTextChange: (String) -> Unit, label: String){
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            readOnly = true,
            value = selectedOption,
            onValueChange = {},
            label = {Text(label)},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach{ option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        expanded = false
                        onTextChange(selectedOption)
                    }
                ) {
                    Text(text = option)
                }
            }
        }
    }
}

@Composable
fun ButtonDiary(icon: ImageVector /**Painter Type**/, label: String, onNavigateToDestination: () -> Unit){
    Button(
        onClick = onNavigateToDestination,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        //Image(painter = image, contentDescription = label)
        Icon(icon, label)
        Text(label)

    }
}

@Composable
fun ButtonSubmit(isValid: Boolean, data: Any){
    Button(
        onClick = {
            // Store data to room local data base then show it on a history list
            if(isValid){
                // add data to local database

            }
        }
    ) {
        Text(text = "Submit")
    }
}