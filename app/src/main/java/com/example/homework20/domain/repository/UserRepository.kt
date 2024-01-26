package com.example.homework20.domain.repository

import com.example.homework20.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun addUser(user: UserDomain)

    suspend fun deleteUser(user: UserDomain)

    suspend fun updateUser(user: UserDomain)

    fun getUserByEmail(email: String): Flow<List<UserDomain>>

}