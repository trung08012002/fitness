package com.example.fit.RemoteDatabase.Login

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fit.data.component.Login.user_login

interface UserLoginDao {
    @Query("SELECT * FROM userlogin WHERE LoginName=(:username) AND Password=(:password)")
    fun getuserloginbynameandpassword(username:String,password:String):LiveData<user_login>
    @Insert
    fun insertuserlogin(userLogin: user_login)
    @Delete
    fun deleteuserlogin(userLogin: user_login)
    @Update
    fun updateuserlogin(userLogin: user_login)
}