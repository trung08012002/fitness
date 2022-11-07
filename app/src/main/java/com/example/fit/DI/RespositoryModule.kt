package com.example.fit.DI

import com.example.fit.data.component.Login.ILoginRespository
import com.example.fit.data.component.Login.RoomRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RespositoryModule {
    @Binds
    @Singleton
    abstract fun bindloginrepository(impl: RoomRespository): ILoginRespository
}