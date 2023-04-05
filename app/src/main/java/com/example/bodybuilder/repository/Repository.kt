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
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {
    private val _bmiState = MutableStateFlow(BmiData(0,"",0.toFloat(), 0.toFloat(), ""))
    val bmiState: StateFlow<BmiData> = _bmiState.asStateFlow()

    // might want a third parameter for either Metric or Imperial
    suspend fun getBMI(weight: Float, height: Float){
        withContext(Dispatchers.IO){
            // Network
            val result : Response<BmiData> = api.getBmi(Constants.KEY, Constants.HOST, 27, weight, height)
            if (result.isSuccessful){
                Log.i("REPO", result.code().toString())
            }else {
                result.errorBody()?.string()?.let { Log.e("REPO ERR", it.toString()) }
            }
//                val bmiResult: BmiData = api.getImperial(Constants.KEY, Constants.HOST, weight, height)
//                Log.i("API", bmiResult.toString())

            // Database
            //bmiDao.insertBmi(bmiResult)
            //_bmiState.value = bmiDao.getBmi()
        }
    }
}