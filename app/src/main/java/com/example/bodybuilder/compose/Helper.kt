package com.example.bodybuilder.compose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldAge(age:String, onTextChange: (String) -> Unit, imeAction: ImeAction){
    TextField(
        value = age,
        onValueChange = {
            if (it.length <= 6){
                onTextChange
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
                onTextChange
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
                onTextChange
            }
        },
        label = {Text("Height(cm)")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction)
    )
}