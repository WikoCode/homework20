package com.example.homework20.di

import android.app.Application
import androidx.room.Room
import com.example.homework20.data.local.dao.UserDao
import com.example.homework20.data.local.database.UserDatabase
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
    fun provideYourDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(app, UserDatabase::class.java, "User_Database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideYourDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

}