package com.example.bodybuilder.viewmodels

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: Repository,
//    private val context: Context
) : ViewModel(){

    /**
     * Insert images' path file to Room DB
     */
    fun insertImagesToDatabase(date: String, listImages : List<Uri>){
//        val imagesBitmap = mutableListOf<Bitmap>()
//        images.forEach {
//            val source = ImageDecoder.createSource(context.contentResolver, it)
//            val bitmap = ImageDecoder.decodeBitmap(source)
//            imagesBitmap.add(bitmap)
//        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertImagesToDB(date, listImages.map{it.toString()})
        }
    }
}