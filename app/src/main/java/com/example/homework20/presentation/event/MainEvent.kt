package com.example.homework20.presentation.event

import com.example.homework20.presentation.model.UserPresentation

sealed class MainEvent {

    data class AddUser(val user: UserPresentation) : MainEvent()
    data class DeleteUser(val user: UserPresentation) : MainEvent()
    data class UpdateUser(val user: UserPresentation) : MainEvent()

}