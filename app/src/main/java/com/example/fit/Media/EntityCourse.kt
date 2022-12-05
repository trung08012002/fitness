package com.example.fit.Media



import com.google.gson.annotations.SerializedName

data class EntityCourse(
    @SerializedName("IdCourse")
    val IdCourse:Int,
    @SerializedName("ImgUrl")
    val ImgUrl:String,
    @SerializedName("VideoUrl")
    val VideoUrl:String,
    @SerializedName("NameCourse")
    val NameCourse:String,
    @SerializedName("Description")
    val Description:String,
    @SerializedName("Time")
    val Time:Int,
    @SerializedName("NumberWorkouts")
    val NumberWorkouts:Int
)