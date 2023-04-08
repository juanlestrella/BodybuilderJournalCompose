package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val tag = ProfileViewModel::class.simpleName

    private val _bmiState = MutableStateFlow<BmiData>(BmiData(0.toFloat(), "",""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    fun insertBmi(age: String, weight: String, height: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertBmi(age.toInt(), weight.toFloat(), height.toFloat())
            } catch (e: Exception){
                e.message?.let { Log.e(tag, it) }
            }
        }
    }

    fun getBmi(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _bmiState.value = repository.getBmi()
                //Log.i(tag, bmiState.toString())
            } catch (e : Exception) {
                e.message?.let { Log.e(tag, it) }
            }
        }
    }
}