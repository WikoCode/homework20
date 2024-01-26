package com.example.homework20.domain.usecase

import javax.inject.Inject

class FieldsValidatorUseCase @Inject constructor() {
    fun areValidFields(vararg fields: String): Boolean {
        return fields.all { it.isNotEmpty() }
    }
}