package com.example.homework20.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homework20.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email = :email")
    fun getUsersByEmail(email: String): Flow<List<UserEntity>>
}