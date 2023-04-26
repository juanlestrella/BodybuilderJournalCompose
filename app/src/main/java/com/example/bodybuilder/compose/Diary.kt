package com.example.bodybuilder.compose


import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bodybuilder.ui.theme.Bodybuilder

@Composable
fun DiaryScreen(
    modifier: Modifier,
    onNavigateToBmi: () -> Unit,
    onNavigateToBodyFat: () -> Unit,
    onNavigateToDailyCalorie: () -> Unit,
    onNavigateToMacros: () -> Unit
) {
    DiaryBodyContent(
        modifier = modifier,
        onNavigateToBmi = onNavigateToBmi,
        onNavigateToBodyFat = onNavigateToBodyFat,
        onNavigateToDailyCalorie = onNavigateToDailyCalorie,
        onNavigateToMacros = onNavigateToMacros
    )
}

@Composable
fun DiaryBodyContent(
    modifier: Modifier,
    onNavigateToBmi: () -> Unit,
    onNavigateToBodyFat: () -> Unit,
    onNavigateToDailyCalorie: () -> Unit,
    onNavigateToMacros: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
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