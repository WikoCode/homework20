package com.example.homework20.domain.usecase

import com.example.homework20.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String) = userRepository.getUserByEmail(email)
}