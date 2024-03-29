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

    // live observer of all BMIs
    private val _bmiList: StateFlow<List<BmiData>> = repository.bmiList
    val bmiList: StateFlow<List<BmiData>> = _bmiList

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
     * Insert the given BMI to the Room database
     */
    fun insertBmiToDatabase(data: BmiData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBmiToDB(data)
        }
    }
    init{
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllBmiFromDB()
        }
    }
}