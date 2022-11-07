package com.example.fit.data.component.Login


import com.auth0.android.result.UserProfile
import com.example.fit.ApplicationUtil.await
import com.example.fit.data.StateApplication.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseAuth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
       return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e:Exception)
        {
           Resource.Failure(e)
        }
    }

    override suspend fun signup(
        loginName: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
       return try {
           val result=firebaseAuth.createUserWithEmailAndPassword(email,password).await()
           result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(loginName).build())
           Resource.Success(result.user!!)
       }catch (e:Exception)
       {
           e.printStackTrace()
           Resource.Failure(e)
       }
    }

    override fun logout() {
         firebaseAuth.signOut()
    }

}