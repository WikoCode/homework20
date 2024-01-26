package com.example.homework20.domain.usecase

import javax.inject.Inject

class EmailValidatorUseCase @Inject constructor() {
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        return emailRegex.matches(email)
    }
}