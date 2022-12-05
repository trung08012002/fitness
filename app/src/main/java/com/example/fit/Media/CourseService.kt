package com.example.fit.Media

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface CourseService {
    @GET(Constants.GETALLCOURSE_URL)
    suspend fun getallcourse(@Query("getcourse") allcourse:Int) : Response<List<EntityCourse>>
    @GET(Constants.GETCOURSEBYID)
    suspend fun getcoursebyid(@Query("getcourse") allcourse: Int,@Query("IDCOURSE") IDCOURSE:Int):Response<EntityCourse>
}