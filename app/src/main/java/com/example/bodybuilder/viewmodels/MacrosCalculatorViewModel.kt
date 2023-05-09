package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.data.MacrosData.MacrosData
import com.example.bodybuilder.data.MacrosData.MacrosDetail
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

    private val _macros = MutableStateFlow(
        MacrosData(
            0.toFloat(),
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // balanced
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // low fat
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // low carbs
            MacrosDetail(0.toFloat(),0.toFloat(),0.toFloat()), // high protein
        )
    )
    val macros : StateFlow<MacrosData> = _macros.asStateFlow()

    private val _macrosList: StateFlow<List<MacrosData>> = repository.macrosList
    val macrosList : StateFlow<List<MacrosData>> = _macrosList

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
                _macros.value = repository.macros.value
            } catch (e: Exception) {
                e.message?.let { Log.e(tag, it) }
            }
        }
    }

    fun insertMacrosToDatabase(data: MacrosData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertMacrosToDB(data)
        }
    }
    init{
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllMacrosFromDB()
        }
    }
}