package com.example.fit.DI

import android.content.Context
import androidx.room.Room
import com.example.fit.RemoteDatabase.Login.UserLoginDao
import com.example.fit.RemoteDatabase.Login.User_Login_Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
 @Provides
 @Singleton
 fun providelogindao(userLoginDatabase: User_Login_Database): UserLoginDao {
  return userLoginDatabase.userLoginDao()
 }

 @Provides
 @Singleton
 fun provideLoginDatabase(@ApplicationContext appContext: Context):User_Login_Database
 {
    return Room.databaseBuilder(appContext,User_Login_Database::class.java, "loginuserdatabase")
     .addMigrations()
     .build()
 }
}
