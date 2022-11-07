package com.example.fit.DI

import com.example.fit.data.component.Login.AuthRepository
import com.example.fit.data.component.Login.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {
    @Provides
    fun provideFirebaseAuth():FirebaseAuth=Firebase.auth

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository =impl
}