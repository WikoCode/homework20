package com.example.homework20.presentation.mapper

import com.example.homework20.domain.model.UserDomain
import com.example.homework20.presentation.model.UserPresentation

fun UserDomain.toPresentation() : UserPresentation {
    return UserPresentation(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age
    )
}