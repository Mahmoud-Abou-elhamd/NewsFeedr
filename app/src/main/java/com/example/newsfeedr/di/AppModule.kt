package com.example.newsfeedr.di

import android.app.Application
import androidx.room.Room
import com.example.newsfeedr.data.local.NewsDao
import com.example.newsfeedr.data.local.NewsDatabase
import com.example.newsfeedr.data.local.NewsTypeConvertor
import com.example.newsfeedr.data.manager.LocalUserMangerImpl
import com.example.newsfeedr.data.remote.NewsApi
import com.example.newsfeedr.data.repository.NewsRepositoryImpl
import com.example.newsfeedr.domain.manager.LocalUserManger
import com.example.newsfeedr.domain.repository.NewsRepository
import com.example.newsfeedr.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsfeedr.domain.usecases.app_entry.ReadAppEntry
import com.example.newsfeedr.domain.usecases.app_entry.SaveAppEntry
import com.example.newsfeedr.domain.usecases.news.DeleteArticle
import com.example.newsfeedr.domain.usecases.news.NewsUseCases
import com.example.newsfeedr.domain.usecases.news.SearchNews
import com.example.newsfeedr.domain.usecases.news.SelectArticle
import com.example.newsfeedr.domain.usecases.news.SelectArticles
import com.example.newsfeedr.domain.usecases.news.UpsertArticle
import com.example.newsfeedr.util.Constants.BASE_URL
import com.example.newsfeedr.util.Constants.NEWS_DATABASE_NAME
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}