package com.example.bodybuilder.compose


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.bodybuilder.ui.theme.Bodybuilder

@Composable
fun DiaryScreen(
    onNavigateToBmi: () -> Unit,
    onNavigateToBodyFat: () -> Unit,
    onNavigateToDailyCalorie: () -> Unit,
    onNavigateToMacros: () -> Unit
) {
    DiaryBodyContent(
        onNavigateToBmi = onNavigateToBmi,
        onNavigateToBodyFat = onNavigateToBodyFat,
        onNavigateToDailyCalorie = onNavigateToDailyCalorie,
        onNavigateToMacros = onNavigateToMacros
    )
}

@Composable
fun DiaryBodyContent(
    modifier: Modifier = Modifier,
    onNavigateToBmi: () -> Unit,
    onNavigateToBodyFat: () -> Unit,
    onNavigateToDailyCalorie: () -> Unit,
    onNavigateToMacros: () -> Unit
) {
    Column {
        ButtonDiary(icon = Icons.Filled.Edit, label = "Body Mass Index", onNavigateToDestination = onNavigateToBmi)
        ButtonDiary(icon = Icons.Filled.Edit, label = "Body Fat", onNavigateToDestination = onNavigateToBodyFat)
        ButtonDiary(icon = Icons.Filled.Edit, label = "Daily Calorie", onNavigateToDestination = onNavigateToDailyCalorie)
        ButtonDiary(icon = Icons.Filled.Edit, label = "Macros", onNavigateToDestination = onNavigateToMacros)
    }

}
@Preview(showBackground = true)
@Composable
fun Diary_Screen_Preview(){
    Bodybuilder {
        //DiaryScreen()
    }
}