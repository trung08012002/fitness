package com.example.fit.data.component.Login

import androidx.lifecycle.LiveData

interface ILoginRespository {
  fun getloginuser(loginname:String,password:String):LiveData<user_login>?
   suspend fun addloginuser(userLogin: user_login)
   suspend fun updateloginuser(userLogin: user_login)
   suspend fun deleteloginuser(userLogin: user_login)
}