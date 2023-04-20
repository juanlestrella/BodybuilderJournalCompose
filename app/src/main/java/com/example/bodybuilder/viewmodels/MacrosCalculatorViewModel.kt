package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.data.MacrosAmountData.MacrosAmountData
import com.example.bodybuilder.data.MacrosAmountData.MacrosData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MacrosCalculatorViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val tag = MacrosCalculatorViewModel::class.simpleName

    private val _macroCalculator = MutableStateFlow(
        MacrosAmountData(
            0.toFloat(),
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // balanced
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // low fat
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // low carbs
            MacrosData(0.toFloat(),0.toFloat(),0.toFloat()), // high protein
        )
    )
    val macroCalculator : StateFlow<MacrosAmountData> = _macroCalculator.asStateFlow()

    fun getMacrosCalculatorFromApi(
        age: String,
        gender: String,
        height: String,
        weight: String,
        activityLevel: Int,
        goal: String
    ){
        viewModelScope.launch(Dispatchers.IO){
            try{
                repository.getMacrosCalculatorFromApi(
                    age.toInt(),
                    gender,
                    height.toFloat(),
                    weight.toFloat(),
                    activityLevel,
                    goal
                )
                _macroCalculator.value = repository.macroCalculator.value
            } catch (e: Exception) {
                e.message?.let { Log.e(tag, it) }
            }
        }
    }
}