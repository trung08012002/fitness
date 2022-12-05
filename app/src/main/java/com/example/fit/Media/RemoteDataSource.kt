package com.example.fit.Media

import android.util.Log
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val courseService: CourseService) {
    suspend fun getallcourse():Response<List<EntityCourse>>{
        return courseService.getallcourse(1)
    }
    suspend fun getcoursebyid(Id:Int):Response<EntityCourse>
    {
        return courseService.getcoursebyid(1,Id)
    }
}