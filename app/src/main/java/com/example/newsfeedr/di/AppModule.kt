package com.example.newsfeedr.di

import android.app.Application
import com.example.newsfeedr.data.manager.LocalUserMangerImpl
import com.example.newsfeedr.data.remote.NewsApi
import com.example.newsfeedr.data.repository.NewsRepositoryImpl
import com.example.newsfeedr.domain.manager.LocalUserManger
import com.example.newsfeedr.domain.repository.NewsRepository
import com.example.newsfeedr.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsfeedr.domain.usecases.app_entry.ReadAppEntry
import com.example.newsfeedr.domain.usecases.app_entry.SaveAppEntry
import com.example.newsfeedr.domain.usecases.news.NewsUseCases
import com.example.newsfeedr.domain.usecases.news.SearchNews
import com.example.newsfeedr.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            searchNews = SearchNews(newsRepository)
        )
    }
}