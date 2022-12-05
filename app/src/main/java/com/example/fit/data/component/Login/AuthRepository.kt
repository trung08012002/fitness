package com.example.fit.data.component.Login

import androidx.lifecycle.MutableLiveData
import com.example.fit.data.StateApplication.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun login(email:String,password:String):Flow<Resource<FirebaseUser>>
    suspend fun signup(loginName:String,email:String,password: String):Flow<Resource<FirebaseUser>>
    fun logout()
    fun getLoggedOutLiveData():MutableLiveData<Boolean>
    fun getLoggedUser():Flow<Resource<FirebaseUser>>

}
