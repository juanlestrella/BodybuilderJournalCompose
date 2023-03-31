package com.example.bodybuilder.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bodybuilder.database.getBmiDB
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.repositories.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) // might have to use regular ViewModel(), need viewmodel factory 
    : AndroidViewModel(application) // Can use Dagger Injection too
{
    // might need to learn Hilt to connect Repository to Room
    private val repository: Repository = Repository(getBmiDB(application))

    private val _bmiState = MutableStateFlow(BmiData("",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    fun getBMI(weight: Float, height: Float){
        viewModelScope.launch {
            try {
                //repository.getBMI(_weightState.value.toFloat(), _heightState.value.toFloat())
                repository.getBMI(weight, height)
                _bmiState.value = repository.bmiState.value
            } catch (e: Exception){
                e.message?.let { Log.e("PROFILE VIEW MODEL", it) }
            }
        }
    }
}

class ProfileViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ProfileViewModelFactory::class.java)) {
            return ProfileViewModel(application) as T
        }
        throw IllegalArgumentException()
    }
}