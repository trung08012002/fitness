package com.example.fit.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit.data.StateApplication.Resource
import com.example.fit.data.component.Login.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ModelLogin @Inject constructor(private val authRepository: AuthRepository):ViewModel(){
    private val _loginFlow= MutableLiveData<Resource<FirebaseUser>>(null)
    val loginFlow:LiveData<Resource<FirebaseUser>?> = _loginFlow
    private val _signupFlow= MutableLiveData<Resource<FirebaseUser>>(null)
    val signupFlow:LiveData<Resource<FirebaseUser>?> = _signupFlow
    init {
        viewModelScope.async(Dispatchers.IO){
            var result=authRepository.getLoggedUser()
            withContext(Dispatchers.IO)
            {
                _loginFlow.value=result.asLiveData(viewModelScope.coroutineContext).value
            }
        }
    }
    fun signup( LoginName:String,Email:String,Password:String){

        viewModelScope.async(Dispatchers.IO)
        {

             val result =authRepository.signup(LoginName,Email,Password)

            withContext(Dispatchers.Main)
            {
                _signupFlow.value=result.asLiveData(viewModelScope.coroutineContext).value
            }
        }
    }
    fun login( Email:String,Password:String)
    {
        viewModelScope.async(Dispatchers.IO)
        {
            val result=authRepository.login(Email,Password)
            withContext(Dispatchers.Main)
            {
                _loginFlow.value=result.asLiveData(viewModelScope.coroutineContext).value
            }
        }
    }



}