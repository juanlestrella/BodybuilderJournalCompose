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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bodybuilder.viewmodels.MacrosCalculatorViewModel

@Composable
fun MacroCalculatorScreen(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Macro Calculator", color = Color.Red) },
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
        MacroCalculatorContent(contentPaddingValues = contentPadding)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MacroCalculatorContent(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
) {
    val context = LocalContext.current as Activity

    val viewModel: MacrosCalculatorViewModel = hiltViewModel()

    //todo: textfields and spinners
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
        modifier = modifier.padding(paddingValues = contentPaddingValues)
    ){
        TextFieldAge(age = age, onTextChange = {age = it}, imeAction = ImeAction.Next)
        Spinner(selected = selectedGender, options = genderOptions,onTextChange = {selectedGender = it})
        TextFieldHeight(height = height, onTextChange = {height = it}, imeAction = ImeAction.Next)
        TextFieldWeight(weight = weight, onTextChange = {weight = it}, imeAction = ImeAction.Next)
        Spinner(selected = activityLevelSelected.toString(), options = activityLevelsOptions.map{it.toString()}.toList(),onTextChange = {activityLevelSelected = it.toInt()})
        Spinner(selected = selectedGoal, options = goalOptions,onTextChange = {selectedGoal = it})
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
                    Toast.makeText(context, macroCalculator.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        ){
            Text(text = "Submit")
        }
    }

}
