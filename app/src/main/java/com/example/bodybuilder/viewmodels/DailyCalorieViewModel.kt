package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieData
import com.example.bodybuilder.data.DailyCalorieData.DailyCalorieGoalsData
import com.example.bodybuilder.data.DailyCalorieData.GainWeightData
import com.example.bodybuilder.data.DailyCalorieData.LossWeightData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyCalorieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
    private val tag = DailyCalorieViewModel::class.simpleName

    private val _dailyCalorie = MutableStateFlow(
        DailyCalorieData(
            0,
            DailyCalorieGoalsData(
                0,
                LossWeightData("", 0),
                LossWeightData("", 0),
                LossWeightData("", 0),
                GainWeightData("",0),
                GainWeightData("",0),
                GainWeightData("",0)
            )
        )
    )
    val dailyCalorie : StateFlow<DailyCalorieData> = _dailyCalorie.asStateFlow()

    fun getDailyCalorieFromApi(
        age: String,
        gender: String,
        height: String,
        weight: String,
        activityLevel: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repository.getDailyCalorieFromApi(age.toInt(), gender, height.toFloat(), weight.toFloat(), activityLevel)
                _dailyCalorie.value = repository.dailyCalorie.value
            }catch (e: Exception){
                e.message?.let { Log.e(tag, it) }
            }
        }
    }
}