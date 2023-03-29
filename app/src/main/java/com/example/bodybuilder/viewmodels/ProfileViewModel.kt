package com.example.bodybuilder.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.entities.BMIResult
import com.example.bodybuilder.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository: Repository = Repository()

    private val _bmiState = MutableStateFlow(BMIResult("",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BMIResult> = _bmiState.asStateFlow()

    private val _weightState = MutableStateFlow("")
    val weightState: StateFlow<String> = _weightState

    private val _heightState = MutableStateFlow("")
    val heightState: StateFlow<String> = _heightState

    fun getBMI(){
        viewModelScope.launch {
            try {
                repository.getBMI(_weightState.value.toFloat(), _heightState.value.toFloat())
            } catch (e: Exception){
                e.message?.let { Log.e("PROFILE VIEW MODEL", it) }
            }
        }
    }
}