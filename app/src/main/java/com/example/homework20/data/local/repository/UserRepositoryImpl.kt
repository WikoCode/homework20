package com.example.homework20.data.local.repository

import com.example.homework20.data.local.dao.UserDao
import com.example.homework20.data.local.mapper.toData
import com.example.homework20.data.local.mapper.toDomain
import com.example.homework20.domain.model.UserDomain
import com.example.homework20.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun addUser(user: UserDomain) {
        userDao.insert(user.toData())
    }

    override suspend fun deleteUser(user: UserDomain) {
        userDao.delete(user.toData())
    }

    override suspend fun updateUser(user: UserDomain) {
        userDao.update(user.toData())
    }

    override fun getUserByEmail(email: String): Flow<List<UserDomain>> {
        return userDao.getUsersByEmail(email)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }
}