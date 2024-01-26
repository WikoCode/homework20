package com.example.homework20.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework20.domain.usecase.AddUserUseCase
import com.example.homework20.domain.usecase.DeleteUserUseCase
import com.example.homework20.domain.usecase.EmailValidatorUseCase
import com.example.homework20.domain.usecase.FieldsValidatorUseCase
import com.example.homework20.domain.usecase.GetUserByEmailUseCase
import com.example.homework20.domain.usecase.UpdateUserUseCase
import com.example.homework20.presentation.event.MainEvent
import com.example.homework20.presentation.mapper.toDomain
import com.example.homework20.presentation.model.UserPresentation
import com.example.homework20.presentation.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val fieldsValidatorUseCase: FieldsValidatorUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> get() = _state

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.AddUser -> addUser(event.user)
            is MainEvent.DeleteUser -> deleteUser(event.user.email)
            is MainEvent.UpdateUser -> updateUser(event.user)
        }
    }

    private fun addUser(userPresentation: UserPresentation) {
        viewModelScope.launch {
            if (!isValidUser(userPresentation)) {
                _state.update { it.copy(errorMessage = "Invalid user data") }
                return@launch
            }

            try {
                val existingUsers = getUserByEmailUseCase(userPresentation.email).first()
                if (existingUsers.isNotEmpty()) {
                    _state.update { it.copy(errorMessage = "User already exists") }
                } else {
                    addUserUseCase(userPresentation.toDomain())
                    _state.update { it.copy(successMessage = "User added successfully") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = "Error adding user: ${e.message}") }
            }
        }
    }

    private fun deleteUser(email: String) {
        viewModelScope.launch {
            try {
                val userToDelete = getUserByEmailUseCase(email).first().firstOrNull()
                if (userToDelete == null) {
                    _state.update { it.copy(errorMessage = "User not found") }
                } else {
                    deleteUserUseCase(userToDelete)
                    _state.update { it.copy(successMessage = "User deleted successfully") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = "Error deleting user: ${e.message}") }
            }
        }
    }

    private fun updateUser(userPresentation: UserPresentation) {
        viewModelScope.launch {
            if (!isValidUser(userPresentation)) {
                _state.update { it.copy(errorMessage = "Invalid user data") }
                return@launch
            }

            try {
                val userToUpdate = getUserByEmailUseCase(userPresentation.email).first().firstOrNull()
                if (userToUpdate == null) {
                    _state.update { it.copy(errorMessage = "User not found") }
                } else {
                    updateUserUseCase(userPresentation.toDomain())
                    _state.update { it.copy(successMessage = "User updated successfully") }
                }
            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = "Error updating user: ${e.message}") }
            }
        }
    }

    private fun isValidUser(user: UserPresentation): Boolean {
        val areFieldsValid = fieldsValidatorUseCase.areValidFields(
            user.firstName,
            user.lastName,
            user.email,
            user.age
        )

        if (areFieldsValid) {
            return emailValidatorUseCase.isValidEmail(user.email)
        } else {
            return false
        }
    }



    fun resetMessages() {
        _state.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
