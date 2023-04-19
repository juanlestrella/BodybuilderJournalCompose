package com.example.bodybuilder.repository

import android.util.Log
import android.widget.Toast
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.data.BmiData.BmiData
import com.example.bodybuilder.data.BodyFatData.BodyFatData
import com.example.bodybuilder.response.BmiResponse
import com.example.bodybuilder.network.ApiService
import com.example.bodybuilder.response.BodyFatResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {

    private val tag = Repository::class.simpleName

    private val _bmi = MutableStateFlow<BmiData>(BmiData(0.toFloat(), "", ""))
    val bmi: StateFlow<BmiData> = _bmi.asStateFlow()

    private val _bodyFat = MutableStateFlow<BodyFatData>(BodyFatData(0.toFloat(), "", 0.toFloat(), 0.toFloat(),0.toFloat()))
    val bodyFat: StateFlow<BodyFatData> = _bodyFat.asStateFlow()

    suspend fun getBmiFromApi(age: Int, weight: Float, height: Float){
        withContext(Dispatchers.IO){
            val response : Response<BmiResponse> = api.getResponseBmi(Constants.KEY, Constants.HOST, age, weight, height)
            if (response.isSuccessful){
                _bmi.value = response.body()!!.data
                //Log.i("REPO", bmiState.toString())
            }else {
                response.errorBody()?.string()?.let { Log.e(tag, "$it(BMI ERROR)") }
            }
        }
    }

//    suspend fun insertBmiToDatabase(){
//        // if (_bmiResponse.value) // check if stateflow has actual value
//        bmiDao.insertBmi(_bmi.value)
//    }

//    suspend fun getBmiFromDatabase() : BmiData {
//        Log.i("REPO", bmiDao.getBmi().toString())
//        return bmiDao.getBmi()
//    }

    suspend fun getBodyFatFromApi(
        age: Int,
        gender: String,
        weight: Number,
        height: Number,
        neck: Number,
        waist: Number,
        hip: Number
    ){
        withContext(Dispatchers.IO){
            val response : Response<BodyFatResponse> =
                api.getResponseBodyFat(
                    Constants.KEY,
                    Constants.HOST,
                    age,
                    gender,
                    weight,
                    height,
                    neck,
                    waist,
                    hip
                )
            if (response.isSuccessful) {
                _bodyFat.value = response.body()!!.data
            } else {
                response.errorBody()?.string()?.let { Log.e(tag, "$it(Body Fat Error)") }
            }
        }
    }
}