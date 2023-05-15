package com.example.bodybuilder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.bodybuilder.navigation.AppBottomNavigation
import com.example.bodybuilder.navigation.AppNavHost
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                            title = { Text("Body Building Journal", color = MaterialTheme.colors.primary) },
                            backgroundColor = MaterialTheme.colors.background,
                        )
                    },

                    content = { paddingValues ->
                        AppNavHost(
                            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                            navController = navController
                        )
                    },

                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                )
            }
        }
    }
}



