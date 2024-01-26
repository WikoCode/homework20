package com.example.homework20.data.local.mapper

import com.example.homework20.data.local.model.UserEntity
import com.example.homework20.domain.model.UserDomain

fun UserEntity.toDomain(): UserDomain {
    return UserDomain(
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}