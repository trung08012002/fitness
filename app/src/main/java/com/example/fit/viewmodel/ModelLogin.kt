package com.example.fit.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit.data.component.Login.AuthRepository

import com.example.fit.data.StateApplication.Resource
import com.google.firebase.auth.FirebaseUser

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ModelLogin @Inject constructor(private val authRepository: AuthRepository):ViewModel(){
    private val _loginFlow= MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow:StateFlow<Resource<FirebaseUser>?> = _loginFlow
    private val _signupFlow= MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow:StateFlow<Resource<FirebaseUser>?> = _signupFlow
    val currentUser:FirebaseUser?
     get() = authRepository.currentUser
    init {
        if(authRepository.currentUser!=null)
        {
            _loginFlow.value= Resource.Success(authRepository.currentUser!!)
        }
    }
    fun signup( LoginName:String,Email:String,Password:String){

        viewModelScope.async(Dispatchers.IO)
        {
            _signupFlow.value=Resource.Loading

             val result =authRepository.signup(LoginName,Email,Password)

            withContext(Dispatchers.Main)
            {
                _signupFlow.value=result

            }
        }
    }
    fun login( Email:String,Password:String)
    {
        viewModelScope.async(Dispatchers.IO)
        {
            _loginFlow.value=Resource.Loading
            val result=authRepository.login(Email,Password)
            withContext(Dispatchers.Main)
            {
                _loginFlow.value=result
            }
        }
    }
}