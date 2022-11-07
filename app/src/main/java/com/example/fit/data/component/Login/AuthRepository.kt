package com.example.fit.data.component.Login

import com.example.fit.data.StateApplication.Resource
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun login(email:String,password:String):Resource<FirebaseUser>
    suspend fun signup(loginName:String,email:String,password: String):Resource<FirebaseUser>
    fun logout()
}
