package com.example.bodybuilder.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    /**
     * Insert images' path file to Room DB
     */
    fun insertImagesToDatabase(images : List<Uri>){
        val imagesString: List<String> = images.map { it.toString() }
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertImagesToDB(imagesString)
        }
    }
}