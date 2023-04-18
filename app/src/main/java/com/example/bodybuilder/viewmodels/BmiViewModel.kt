package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.data.BmiData.BmiData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel()
{
    private val tag = BmiViewModel::class.simpleName

    private val _bmiState = MutableStateFlow(BmiData(0.toFloat(), "",""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    /**
     * Send the api User's input then insert the response in _bmiState.value
     */
    fun getBmiFromApi(age: String, weight: String, height: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getBmiFromApi(age.toInt(), weight.toFloat(), height.toFloat())
                _bmiState.value = repository.bmi.value
            } catch (e: Exception){
                e.message?.let { Log.e(tag, it) }
            }
        }
    }

    /**
     * TODO: Get the history of all inserted bmi
     */
    fun getAllBmiFromDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //_bmiState.value = repository.getBmiFromDatabase()
                //Log.i(tag, bmiState.toString())
            } catch (e : Exception) {
                e.message?.let { Log.e(tag, it) }
            }
        }
    }
}