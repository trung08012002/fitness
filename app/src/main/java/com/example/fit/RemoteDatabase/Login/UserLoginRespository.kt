package com.example.fit.RemoteDatabase.Login

import androidx.lifecycle.LiveData
import com.example.fit.data.component.Login.user_login

interface UserLoginRespository {
     fun getuserloginbynameandpassword(username:String,password:String):LiveData<user_login>
      suspend fun insertuserlogin(userLogin: user_login)
      suspend fun updateuserlogin(userLogin: user_login)
      suspend fun deleteuserlogin(userLogin: user_login)
}