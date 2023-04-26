package com.example.bodybuilder.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bodybuilder.compose.*

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "diary"
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable("home") {
            HomeScreen(

            )
        }
        composable("diary") {
            DiaryScreen(
                onNavigateToBmi = { navController.navigate("bmi") },
                onNavigateToBodyFat = { navController.navigate("bodyFat") },
                onNavigateToDailyCalorie = { navController.navigate("dailyCalorie") },
                onNavigateToMacros = { navController.navigate("macros") },
            )
        }
        composable("bmi") {
            BmiScreen(

            )
        }
        composable("bodyFat") {
            BodyFatScreen(

            )
        }
        composable("dailyCalorie") {
            DailyCalorieScreen(

            )
        }
        composable("macros") {
            MacroCalculatorScreen(

            )
        }
    }
}