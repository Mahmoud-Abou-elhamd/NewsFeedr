package com.example.newsfeedr.di

import android.app.Application
import com.example.newsfeedr.data.manager.LocalUserMangerImpl
import com.example.newsfeedr.domain.manager.LocalUserManger
import com.example.newsfeedr.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsfeedr.domain.usecases.app_entry.ReadAppEntry
import com.example.newsfeedr.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}