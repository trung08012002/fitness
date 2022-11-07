package com.example.fit.data.component.Login

import androidx.lifecycle.LiveData
import com.example.fit.ApplicationUtil.LoginApplication
import com.example.fit.RemoteDatabase.Login.UserLoginDao
import javax.inject.Inject
import kotlin.math.log

class RoomRespository @Inject constructor( private val logindao:UserLoginDao): ILoginRespository {


    override  fun getloginuser(loginname:String,password:String):LiveData<user_login>?{
       return logindao.getuserloginbynameandpassword(loginname,password)
    }

    override suspend fun addloginuser(userLogin: user_login) {
       logindao.insertuserlogin(userLogin)
    }

    override suspend fun updateloginuser(userLogin: user_login) {
      logindao.updateuserlogin(userLogin)
    }

    override suspend fun deleteloginuser(userLogin: user_login) {
      logindao.deleteuserlogin(userLogin)
    }

}