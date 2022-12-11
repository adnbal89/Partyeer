package com.partyeer.di

import android.app.Application
import android.content.Context
import com.partyeer.presentation.ui.main.util.UserSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideLocalStorage(application: Application) = UserSharedPreferences(application)

}