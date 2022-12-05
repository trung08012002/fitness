package com.example.fit.DI


import com.example.fit.Respository.UserInforRespository
import com.example.fit.Respository.UserInforRespositoryImpl

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideCloudFireStore():FirebaseFirestore
    {
        return Firebase.firestore
    }
    @Provides
    @Singleton
    fun provideUserInforRepository(impl: UserInforRespositoryImpl): UserInforRespository = impl
}
