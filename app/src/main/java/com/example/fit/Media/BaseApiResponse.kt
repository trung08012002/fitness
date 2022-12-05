package com.example.fit.Media

import android.util.Log
import com.example.fit.data.StateApplication.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): Resource<T>
    {
            Log.d("apicalfail",errorMessage);
            return Resource.Failure("Api call failed $errorMessage")
    }

}