package com.example.fit.RemoteDatabase.Login

import androidx.lifecycle.LiveData
import com.example.fit.ApplicationUtil.LoginApplication
import com.example.fit.data.component.Login.user_login
import javax.inject.Inject

class RoomRespository@Inject constructor(private val userloginDao:UserLoginDao) :UserLoginRespository{

    override fun getuserloginbynameandpassword(
        username: String,
        password: String
    ): LiveData<user_login> {
       return userloginDao.getuserloginbynameandpassword(username,password)
    }

    override suspend fun insertuserlogin(userLogin: user_login) {
        userloginDao.insertuserlogin(userLogin)
    }

    override suspend fun updateuserlogin(userLogin: user_login) {
        userloginDao.updateuserlogin(userLogin)
    }

    override suspend fun deleteuserlogin(userLogin: user_login) {
       userloginDao.deleteuserlogin(userLogin)
    }
}