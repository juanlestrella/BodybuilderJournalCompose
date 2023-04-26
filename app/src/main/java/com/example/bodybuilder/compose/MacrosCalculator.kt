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
import com.example.bodybuilder.viewmodels.MacrosCalculatorViewModel

@Composable
fun MacroCalculatorScreen(
    modifier: Modifier
){
    MacroCalculatorContent(modifier = modifier)
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MacroCalculatorContent(
    modifier: Modifier
) {
    val context = LocalContext.current as Activity

    val viewModel: MacrosCalculatorViewModel = hiltViewModel()

    //todo: text fields and spinners
    var age by rememberSaveable{ mutableStateOf("") }
    val genderOptions = listOf("male", "female")
    var selectedGender: String by rememberSaveable { mutableStateOf(genderOptions[0]) }
    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    /**
     * "1" : "BMR",
     * "2" : "Sedentary: little or no exercise",
     * "3" : "Exercise 1-3 times/week",
     * "4" : "Exercise 4-5 times/week",
     * "5" : "Daily exercise or intense exercise 3-4 times/week",
     * "6" : "Intense exercise 6-7 times/week",
     * "7" : "Very intense exercise daily, or physical job"
     */
    val activityLevelsOptions = (1..7).toList()
    var activityLevelSelected by rememberSaveable { mutableStateOf(activityLevelsOptions[0]) }

    /**
     * "maintain" : "maintain weight",
     * "mildlose" : "Mild weight loss",
     * "weightlose" : "Weight loss",
     * "extremelose" : "Extreme weight loss",
     * "mildgain" : "Mild weight gain",
     * "weightgain" : "Weight gain",
     * "extremegain" : "Extreme weight gain"
     */
    val goalOptions = listOf("maintain","mildlose","weightlose","extremelose","mildgain","weightgain","extremegain")
    var selectedGoal: String by rememberSaveable { mutableStateOf(goalOptions[0])}

    val macroCalculator by viewModel.macroCalculator.collectAsStateWithLifecycle()

    Column (
        modifier = modifier
    ){
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        Spinner(options = genderOptions,onTextChange = {selectedGender = it}, "Gender")
        TextFieldHeight(height = height, onTextChange = {height = it}, imeAction = ImeAction.Next)
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        Spinner(options = activityLevelsOptions.map{it.toString()}.toList(),onTextChange = {activityLevelSelected = it.toInt()}, "Activity Level")
        Spinner(options = goalOptions,onTextChange = {selectedGoal = it}, "Goal")
        TextField(
            value = macroCalculator.toString(),
            onValueChange = {},
            label = { Text(text = "Macro Calculator")},
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
                    viewModel.getMacrosCalculatorFromApi(age, selectedGender, height, weight, activityLevelSelected, selectedGoal)
                }
            }
        ){
            Text(text = "Submit")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MacrosCalculator_Screen_Preview(){
    Bodybuilder {
        MacroCalculatorScreen(Modifier.padding(bottom = 4.dp))
    }
}