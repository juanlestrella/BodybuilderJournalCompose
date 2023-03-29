package com.example.bodybuilder.repositories

import com.example.bodybuilder.Constants
import com.example.bodybuilder.entities.BMIResult
import com.example.bodybuilder.network.BodybuilderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    // might want a third parameter for either Metric or Imperial
    suspend fun getBMI(weight: Float, height: Float){
        withContext(Dispatchers.IO){
            val bmiResult: BMIResult = BodybuilderApi.retrofitService.getImperial(Constants.KEY, Constants.HOST, weight, height)
            // create a room database for bmi result (which has weight, height, bmi, weightCategory)
        }
    }
}