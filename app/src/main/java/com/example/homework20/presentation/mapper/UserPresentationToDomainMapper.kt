package com.example.homework20.presentation.mapper

import com.example.homework20.domain.model.UserDomain
import com.example.homework20.presentation.model.UserPresentation

fun UserPresentation.toDomain() : UserDomain {
    return UserDomain(
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}