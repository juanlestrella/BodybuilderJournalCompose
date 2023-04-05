package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
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

    private val _bmiState = MutableStateFlow<BmiData>(BmiData())
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    fun getBMI(age: String, weight: String, height: String){
        viewModelScope.launch {
            try {
                repository.getBMI(age.toInt(), weight.toFloat(), height.toFloat())
                _bmiState.value = repository.bmiState.value
            } catch (e: Exception){
                e.message?.let { Log.e("PROFILE VIEW MODEL", it.toString()) }
            }
        }
    }
}