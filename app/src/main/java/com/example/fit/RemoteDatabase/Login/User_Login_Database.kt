package com.example.fit.RemoteDatabase.Login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fit.data.component.Login.user_login

@Database(entities = [user_login::class], version = 1)
abstract class User_Login_Database:RoomDatabase(){
    abstract fun userLoginDao():UserLoginDao

}