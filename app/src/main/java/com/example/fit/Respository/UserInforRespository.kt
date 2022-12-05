package com.example.fit.Respository

import com.example.fit.data.StateApplication.Resource
import com.example.fit.data.component.User.PrepareInfor
import com.example.fit.data.component.User.user_infor
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserInforRespository {
    val currentUser: FirebaseUser?
    suspend fun <T> updateSingleFieldPrepare(field:String,value:T):Flow<Resource<Boolean>>
   suspend fun addInforPrepare(prepareInfor: PrepareInfor):Flow<Resource<Boolean>>
    suspend fun getdatausercurrent(): Flow<Resource<user_infor>>
    suspend fun addUserInfor(userInfor: user_infor):Flow<Resource<Boolean>>
    suspend fun deleteInfor(id:String):Flow<Resource<Boolean>>
    suspend fun updateInfor(userInfor: user_infor):Flow<Resource<Boolean>>
}
