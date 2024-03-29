package com.example.bodybuilder.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bodybuilder.compose.*
import com.example.bodybuilder.ui.theme.Bodybuilder
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = "home",
    shouldShowCamera: State<Boolean>,
    executor: ExecutorService,
    directory: File,
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
        composable("home") {
            HomeScreen(
                modifier = modifier
            )
        }
        composable("diary") {
            DiaryScreen(
                modifier = modifier,
                onNavigateToBmi = { navController.navigate("bmi") },
                onNavigateToBodyFat = { navController.navigate("bodyFat") },
                onNavigateToDailyCalorie = { navController.navigate("dailyCalorie") },
                onNavigateToMacros = { navController.navigate("macros") },
            )
        }
        composable("add"){
            AddScreen(modifier = modifier)
        }
        composable("camera"){
            CameraViewScreen(
                shouldShowCamera = shouldShowCamera,
                executor = executor,
                directory = directory
            )
        }
        composable("bmi") {
            BmiScreen(
                modifier = modifier
            )
        }
        composable("bodyFat") {
            BodyFatScreen(
                modifier = modifier
            )
        }
        composable("dailyCalorie") {
            DailyCalorieScreen(
                modifier = modifier
            )
        }
        composable("macros") {
            MacroCalculatorScreen(
                modifier = modifier
            )
        }
    }
}

//@Preview
//@Composable
//fun NavHostPreview(){
//    Bodybuilder {
//        AppNavHost(
//            modifier = Modifier,
//            navController = rememberNavController()
//        )
//    }
//}