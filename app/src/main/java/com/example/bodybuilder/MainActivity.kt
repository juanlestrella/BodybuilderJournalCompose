package com.example.bodybuilder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bodybuilder.compose.*
import com.example.bodybuilder.navigation.AppBottomNavigation
import com.example.bodybuilder.navigation.NavHost
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bodybuilder {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = { Text("Body Building Journal", color = Color.Red) },
                            backgroundColor = MaterialTheme.colors.background,
                        )
                    },
                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                ){
                    NavHost(navController = navController)
                }
            }
        }
    }
}



