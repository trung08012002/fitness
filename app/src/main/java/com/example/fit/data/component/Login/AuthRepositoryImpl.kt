package com.example.fit.data.component.Login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fit.ApplicationUtil.await
import com.example.fit.data.StateApplication.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseAuth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    private  var loggedOutLiveData:MutableLiveData<Boolean> = MutableLiveData(false)
    init {
        if(currentUser!=null)
        {
            loggedOutLiveData.postValue(false)
        }

    }
    override suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>> = flow {
       emit(Resource.Loading)
       try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit((result.user?.let{
                Resource.Success(it)
            }!!))
           loggedOutLiveData.postValue(false)
        } catch (e:Exception)
        {
           Resource.Failure(e.message.toString())
        }
    }

    override suspend fun signup(
        loginName: String,
        email: String,
        password: String
    ): Flow<Resource<FirebaseUser>> = flow{
        emit(Resource.Loading)
      try {
           val result=firebaseAuth.createUserWithEmailAndPassword(email,password).await()
           emit((result.user?.let{
               Resource.Success(it)
           }!!))
          loggedOutLiveData.postValue(false)
       }catch (e:Exception)
       {
           e.printStackTrace()
           Resource.Failure(e.message.toString())
       }
    }

    override fun logout() {
         firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }
    override fun getLoggedOutLiveData(): MutableLiveData<Boolean> {
        return loggedOutLiveData
    }

    override fun getLoggedUser(): Flow<Resource<FirebaseUser>> = flow{
    emit(Resource.Loading)
        if(currentUser!=null)
        {
            loggedOutLiveData.postValue(false)
            emit(Resource.Success(currentUser!!))
        }
        else{
            emit(Resource.Failure("log out success"))
        }
    }
}