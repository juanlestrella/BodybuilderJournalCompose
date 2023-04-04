package com.example.bodybuilder

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bodybuilder.compose.HomeScreen
import com.example.bodybuilder.compose.Profile_Screen
import com.example.bodybuilder.ui.theme.Bodybuilder
import com.example.bodybuilder.viewmodels.ProfileViewModel
import dagger.hilt.EntryPoint
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
                    //val viewModel = hiltViewModel<ProfileViewModel>()
                    //HomeScreen()
                    Profile_Screen()
                }
            }
        }
    }
}



