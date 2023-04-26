package com.example.bodybuilder.compose

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bodybuilder.ui.theme.Bodybuilder
import com.example.bodybuilder.viewmodels.DailyCalorieViewModel

@Composable
fun DailyCalorieScreen(
    modifier: Modifier
){
    DailyCalorieContent(modifier = modifier)
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DailyCalorieContent(
    modifier: Modifier,
) {
    val context = LocalContext.current as Activity

    val viewModel: DailyCalorieViewModel = hiltViewModel()

    var age by rememberSaveable{ mutableStateOf("") }
    val genderOptions = listOf("male", "female")
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }
    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    /**
     * ‘level_1’ : “Sedentary: little or no exercise”,
     * ‘level_2’ : “Exercise 1-3 times/week”,
     * ‘level_3’ : “Exercise 4-5 times/week”,
     * ‘level_4’ : “Daily exercise or intense exercise 3-4 times/week”,
     * ‘level_5’ : “Intense exercise 6-7 times/week”,
     * ‘level_6’ : “Very intense exercise daily, or physical job”
     */
    val activityLevelsOptions = listOf("level_1","level_2","level_3","level_4", "level_5", "level_6")
    val activityLevelSelected by rememberSaveable { mutableStateOf(activityLevelsOptions[0]) }

    val dailyCalorie by viewModel.dailyCalorie.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        Spinner(options = genderOptions,onTextChange = {selectedGender = it}, "Gender")
        TextFieldHeight(height = height, onTextChange = {height = it}, imeAction = ImeAction.Next)
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        Spinner(options = activityLevelsOptions,onTextChange = {activityLevelSelected[0]}, "Activity Level")
        TextField(
            value = dailyCalorie.toString(),
            onValueChange = {},
            label = { Text(text = "Daily Calorie")},
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
                    viewModel.getDailyCalorieFromApi(age, selectedGender, height, weight, activityLevelSelected)
                    //Toast.makeText(context, dailyCalorie.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        ){
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyCalorie_Screen_Preview(){
    Bodybuilder {
        DailyCalorieScreen(Modifier.padding(bottom = 4.dp))
    }
}