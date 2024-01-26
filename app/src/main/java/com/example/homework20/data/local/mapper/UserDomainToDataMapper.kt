package com.example.homework20.data.local.mapper

import com.example.homework20.data.local.model.UserEntity
import com.example.homework20.domain.model.UserDomain

fun UserDomain.toData() : UserEntity {
    return UserEntity(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age
    )
}