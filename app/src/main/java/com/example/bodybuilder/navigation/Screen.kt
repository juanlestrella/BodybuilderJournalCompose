package com.example.bodybuilder.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bodybuilder.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, var icon:ImageVector){
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object Add : Screen("add", R.string.add, Icons.Filled.Add)
    object Diary : Screen("diary", R.string.diary, Icons.Filled.Edit)
    object Bmi : Screen("bmi", R.string.bmi, Icons.Filled.Edit)
    object BodyFat : Screen("bodyFat", R.string.bodyFat, Icons.Filled.Edit)
    object DailyCalorie : Screen("dailyCalorie", R.string.dailyCalorie, Icons.Filled.Edit)
    object Macros : Screen("macros", R.string.macros, Icons.Filled.Edit)
}
