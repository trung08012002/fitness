package com.example.fit.Respository

import com.example.fit.ApplicationUtil.await
import com.example.fit.data.StateApplication.Resource
import com.example.fit.data.component.User.PrepareInfor
import com.example.fit.data.component.User.user_infor
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

class UserInforRespositoryImpl @Inject constructor(
    val db:FirebaseFirestore,
    val firebaseAuth: FirebaseAuth
   ): UserInforRespository {
    override val currentUser: FirebaseUser
      get() = firebaseAuth.currentUser!!
    init{

    }

    override suspend fun addInforPrepare(prepareInfor: PrepareInfor): Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading)
            val userId=currentUser.uid
            db.collection("prepareinfor").document(userId).set(prepareInfor)
            emit(Resource.Success(true))
        }
        catch(e:Exception)
        {
            emit(Resource.Failure(e.message.toString()))
        }
    }
    override suspend fun <T> updateSingleFieldPrepare(field:String, value:T):Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading)
            val userId=currentUser.uid
            db.collection("prepareinfor").document(userId).update(field,value)
            emit(Resource.Success(true))
        }
        catch (e:Exception)
        {
            emit(Resource.Failure(e.message.toString()))
        }


    }


    override suspend fun getdatausercurrent():Flow<Resource<user_infor>> = flow{
        try {
            emit(Resource.Loading)
            val userId = currentUser.uid
            val result =db.collection("users").document(userId)
                .get().result
            emit(Resource.Success(result.toObject(user_infor::class.java)!!))
        }
        catch (e:Exception)
        {
            emit(Resource.Failure(e.message.toString()))
        }

    }

    override suspend fun addUserInfor(userInfor: user_infor)= flow{
        try{
            val userId = currentUser.uid
            emit(Resource.Loading)
            db.collection("users").document(userId).set(userInfor, SetOptions.merge()).await()
            emit(Resource.Success(true))
        }
        catch (e:Exception)
        {
            emit(Resource.Failure(e.message.toString()))
        }

    }

    override suspend fun deleteInfor(id: String) = flow{
         try {
             val userId = currentUser.uid
             emit(Resource.Loading)
             db.collection("users").document(userId).delete().await()
             emit(Resource.Success(true))
         }
         catch (e:Exception)
         {
           emit(Resource.Failure(e.message.toString()))
         }
    }

    override suspend fun updateInfor(userInfor: user_infor) =flow{
        try{
            val userId = currentUser.uid
            emit(Resource.Loading)
            db.collection("users").document(userId).set(userInfor, SetOptions.merge()).await()
            emit(Resource.Success(true))
        }
        catch (e:Exception)
        {
            emit(Resource.Failure(e.message.toString()))
        }
    }
}