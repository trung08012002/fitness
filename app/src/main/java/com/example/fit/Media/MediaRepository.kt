package com.example.fit.Media

import android.util.Log
import com.example.fit.data.StateApplication.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MediaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {



    suspend fun getallcourse(): Flow<Resource<List<EntityCourse>>> {
        return flow {

            val result=safeApiCall { remoteDataSource.getallcourse() }
            Log.d("response",result.toString())
            Log.d("response","remoteDataSource")
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getcoursebyid(Id:Int):Flow<Resource<EntityCourse>>
    {
        return flow {
            val result=safeApiCall { remoteDataSource.getcoursebyid(Id) }
            Log.d("response",result.toString())
            Log.d("response","remoteDataSource")
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}