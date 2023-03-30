package com.example.bodybuilder.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.database.getBmiDB
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) // might have to use regular ViewModel(), need viewmodel factory 
    : ViewModel() // Can use Dagger Injection too
{
    // might need to learn Hilt to connect Repository to Room
    private val repository: Repository = Repository(getBmiDB(application))

    private val _bmiState = MutableStateFlow(BmiData("",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    private val _weightState = MutableStateFlow("")
    val weightState: StateFlow<String> = _weightState

    private val _heightState = MutableStateFlow("")
    val heightState: StateFlow<String> = _heightState

    fun getBMI(){
        viewModelScope.launch {
            try {
                repository.getBMI(_weightState.value.toFloat(), _heightState.value.toFloat())
                _bmiState.value = repository.bmiState.value
            } catch (e: Exception){
                e.message?.let { Log.e("PROFILE VIEW MODEL", it) }
            }
        }
    }
}