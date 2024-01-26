package com.example.homework20.domain.usecase

import com.example.homework20.domain.model.UserDomain
import com.example.homework20.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserDomain) = userRepository.deleteUser(user)
}