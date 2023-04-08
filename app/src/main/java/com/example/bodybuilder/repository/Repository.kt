package com.example.bodybuilder.repository

import android.util.Log
import com.example.bodybuilder.Constants
import com.example.bodybuilder.database.BmiDao
import com.example.bodybuilder.entities.BmiData
import com.example.bodybuilder.entities.BmiResponse
import com.example.bodybuilder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
    private val bmiDao: BmiDao,
) {
    private val tag = Repository::class.simpleName
    suspend fun insertBmi(age: Int, weight: Float, height: Float){
        withContext(Dispatchers.IO){
            // Network
            val response : Response<BmiResponse> = api.getResponseBmi(Constants.KEY, Constants.HOST, age, weight, height)
            if (response.isSuccessful){
                // Insert to BMI Room Database
                bmiDao.insertBmi(response.body()!!.data)
                //Log.i("REPO", bmiState.toString())
            }else {
                response.errorBody()?.string()?.let { Log.e(tag, it.toString()) }
            }
        }
    }

    suspend fun getBmi() : BmiData {
        Log.i("REPO", bmiDao.getBmi().toString())
        return bmiDao.getBmi()
    }
}