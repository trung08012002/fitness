package com.example.fit.Media

import android.provider.ContactsContract.Data
import com.example.fit.viewmodel.DataRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDataRespository():DataRespository
    {
        return DataRespository()
    }
}