package com.example.bodybuilder.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel()
{
    private val tag = ProfileViewModel::class.simpleName

//    private val _bmiState = MutableStateFlow<BmiData>(BmiData())
//    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    fun insertBmi(age: String, weight: String, height: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertBmi(age.toInt(), weight.toFloat(), height.toFloat())
                //_bmiState.value = repository.bmiState.value
            } catch (e: Exception){
                e.message?.let { Log.e(tag, it) }
            }
        }
    }

    fun getBmi() : BmiData? {
        // something wrong with result.postValue, it's not getting the repository.getBmi()
        val result = MutableLiveData<BmiData>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                result.postValue(repository.getBmi())
                //result.value?.let { Log.i(tag, it.toString()) }
                Log.i(tag, repository.getBmi().toString())
            } catch (e : Exception) {
                e.message?.let { Log.e(tag, it) }
            }
        }
        return result.value
    }
}