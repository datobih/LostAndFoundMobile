package com.example.lostandfound.di

import android.content.Context
import android.content.SharedPreferences
import com.example.lostandfound.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE)
    }






}