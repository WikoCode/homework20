package com.example.homework20.presentation.state

data class MainState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
