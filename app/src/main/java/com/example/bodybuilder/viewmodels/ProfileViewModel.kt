package com.example.bodybuilder.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel()
{

    private val _bmiState = MutableStateFlow(BmiData("",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    fun getBMI(weight: Float, height: Float){
        viewModelScope.launch {
            try {
                repository.getBMI(weight, height)
                _bmiState.value = repository.bmiState.value
            } catch (e: Exception){
                e.message?.let { Log.e("PROFILE VIEW MODEL", it) }
            }
        }
    }
}