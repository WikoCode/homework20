package com.example.homework20.presentation.model

data class UserPresentation(
    val email: String,
    val firstName: String,
    val lastName: String,
    val age: String,
) {
    constructor(email: String) : this(email, "", "", "")
}
