package com.example.bodybuilder.repository

import android.util.Log
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDB
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {
    private val _bmiState = MutableStateFlow(BmiData(0,"",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    // might want a third parameter for either Metric or Imperial
    suspend fun getBMI(weight: String, height: String){
        withContext(Dispatchers.IO){
            // Network
            try {
                val bmiResult: BmiData = api.getImperial(Constants.KEY, Constants.HOST, weight, height)
                Log.i("API", bmiResult.toString())
            }catch (e : Exception){
                Log.e("REPOSITY ERROR", e.message!!)
            }

            // Database
            //bmiDao.insertBmi(bmiResult)
            //_bmiState.value = bmiDao.getBmi()
        }
    }
}