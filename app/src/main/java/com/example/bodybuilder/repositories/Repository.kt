package com.example.bodybuilder.repositories

import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDB
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.network.BodybuilderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class Repository(
    private val bmiDB: BmiDB
) {
    private val _bmiState = MutableStateFlow(BmiData("",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    // might want a third parameter for either Metric or Imperial
    suspend fun getBMI(weight: Float, height: Float){
        withContext(Dispatchers.IO){
            val bmiResult: BmiData = BodybuilderApi.retrofitService.getImperial(Constants.KEY, Constants.HOST, weight, height)
            // create a room database for bmi result (which contains weight, height, bmi, weightCategory)
            bmiDB.bmiDao.insertBmi(bmiResult)
            _bmiState.value = bmiDB.bmiDao.getBmi()
        }
    }
}