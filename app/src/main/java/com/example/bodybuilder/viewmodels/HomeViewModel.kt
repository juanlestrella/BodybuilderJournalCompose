package com.example.bodybuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _allImages = MutableStateFlow(listOf<String>())
    val allImages: StateFlow<List<String>> = _allImages.asStateFlow()

    fun getAllImagesFromDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            _allImages.value = repository.getAllImagesFromDB()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO){
            _allImages.value = repository.getAllImagesFromDB()
        }
    }
}