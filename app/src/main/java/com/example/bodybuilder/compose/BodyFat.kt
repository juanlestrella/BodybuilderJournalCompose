package com.example.bodybuilder.compose

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bodybuilder.ui.theme.Bodybuilder
import com.example.bodybuilder.viewmodels.BodyFatViewModel

@Composable
fun BodyFatScreen(modifier: Modifier){
    BodyFatContent(modifier = modifier)
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun BodyFatContent(
    modifier: Modifier
) {
    val context = LocalContext.current as Activity

    val viewModel : BodyFatViewModel = hiltViewModel()

    var age by rememberSaveable{ mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var neck by rememberSaveable{ mutableStateOf("") }
    var waist by rememberSaveable{ mutableStateOf("") }
    var hip by rememberSaveable{ mutableStateOf("") }

    val genderOptions = listOf("male", "female")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }

    val bodyFat by viewModel.bodyFat.collectAsStateWithLifecycle()
    val allBodyFat by viewModel.bodyFatList.collectAsStateWithLifecycle()

    var isBodyFatValid by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        Spinner(options = genderOptions, onTextChange = {selectedGender = it}, "Gender")
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        TextFieldHeight(height = height, onTextChange = {height = it}, imeAction = ImeAction.Next)
        TextFieldNeck(neck = neck, onTextChange = {neck = it}, imeAction = ImeAction.Next)
        TextFieldWaist(waist = waist, onTextChange = {waist = it}, imeAction = ImeAction.Next)
        TextFieldHip(hip = hip, onTextChange = {hip = it}, imeAction = ImeAction.Done)
        TextField(
            value = bodyFat.toString(),
            onValueChange = {},
            label = { Text(text = "Body Fat")},
            readOnly = true
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterHorizontally,
                space = 8.dp
            )
        ){
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
                        viewModel.getBodyFatFromApi(age, selectedGender, weight, height, neck, waist, hip)
                        isBodyFatValid = true // instead of this maybe we need to check if all params are valid for both calculate and submit
                    }
                }
            ){
                Text(text = "Calculate")
            }
            Button(
                onClick = {
                    if (isBodyFatValid){
                        viewModel.insertBodyFatToDatabase(bodyFat)
                    }
                }
            ) {
                Text(text = "Submit")
            }
        }
        /**This can be in the helper file**/
        LazyColumn(
            userScrollEnabled = true
        ){
            stickyHeader {
                Text("History", modifier= modifier.background(Color.Blue))
            }
            items(
                items = allBodyFat,
                itemContent = {item ->
                    Text(text = item.toString())
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyFat_Screen_Preview(){
    Bodybuilder {
        BodyFatScreen(Modifier.padding(bottom = 4.dp))
    }
}
