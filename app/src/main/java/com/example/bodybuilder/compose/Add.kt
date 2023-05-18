package com.example.bodybuilder.compose

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            date = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
        }, year, month, day
    )

    var selectedImages by rememberSaveable{ mutableStateOf(listOf<Uri>())}
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { selectedImages = it }
    )
    var imagesCaption = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)){
            Button( onClick = { datePicker.show() }){
                Text(text = if(date.isNotEmpty()) "$date" else "Select Date")
            }
            Button(
                onClick = {
                    launcher.launch("image/*")

                }
            ){
                Text(text = "Pick Images")
                (1..selectedImages.size).forEach { _ -> imagesCaption.add("") }
            }
        }
        LazyColumn {
            items(selectedImages.size) { index ->
                Card(
                    modifier = Modifier.fillMaxSize(),
                    //border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black)
                ){
                    Column {
                        AsyncImage(
                            model = selectedImages[index],
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxSize()
                        )
                        TextField(
                            value = imagesCaption[index],
                            onValueChange = {
                                imagesCaption.set(index = index, element = it)
                            },
                            label = {Text("Add a short description...")},
                            modifier = Modifier.fillMaxSize().padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


