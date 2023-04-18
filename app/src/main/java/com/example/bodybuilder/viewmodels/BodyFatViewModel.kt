package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.data.BodyFatData.BodyFatData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyFatViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){
    private val tag = BodyFatViewModel::class.simpleName

    private val _bodyFat = MutableStateFlow<BodyFatData>(BodyFatData(0, "", 0, 0,0))
    val bodyFat: StateFlow<BodyFatData> = _bodyFat.asStateFlow()

    fun getBodyFatFromApi(
        age: String,
        gender: String,
        weight: String,
        height: String,
        neck: String,
        waist: String,
        hip: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repository.getBodyFatFromApi(
                    age.toInt(),
                    gender,
                    weight.toFloat(),
                    height.toFloat(),
                    neck.toFloat(),
                    waist.toFloat(),
                    hip.toFloat()
                )
                _bodyFat.value = repository.bodyFat.value
            } catch (e : Exception){
                e.message?.let { Log.e(tag, it) }
            }
        }
    }
}