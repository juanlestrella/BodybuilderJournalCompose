package com.example.bodybuilder.compose

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Calendar

@Composable
fun AddScreen(
    modifier: Modifier
){
    AddBodyContent(modifier = modifier)
}

@Composable
fun AddBodyContent(
    modifier: Modifier,
) {
    /**
     * 0) Date
     * Images
     * Body part's name
     */
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var date by rememberSaveable { mutableStateOf("") }
    // Fetch current year, month, and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            date = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )
    Column(
        modifier = modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Arrangement.CenterHorizontally
    ) {
        Text(text = if(date.isNotEmpty()) "$date" else "Please add a date")

        Button( onClick = { datePicker.show() }){
            Text(text = "Select Date")
        }
    }

}
