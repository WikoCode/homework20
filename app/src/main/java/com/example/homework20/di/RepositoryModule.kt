package com.example.homework20.di

import com.example.homework20.data.local.dao.UserDao
import com.example.homework20.data.local.repository.UserRepositoryImpl
import com.example.homework20.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserAccountRepository(userAccountDao: UserDao): UserRepository {
        return UserRepositoryImpl(userAccountDao)
    }

}