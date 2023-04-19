package com.example.bodybuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.bodybuilder.compose.BmiScreen
import com.example.bodybuilder.compose.BodyFatScreen
import com.example.bodybuilder.compose.DailyCalorieScreen
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bodybuilder {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //HomeScreen()
                    //BmiScreen()
                    //BodyFatScreen()
                    DailyCalorieScreen()
                }
            }
        }
    }
}



