package com.example.homework20.domain.usecase

class FieldsValidatorUseCase {
    fun areValidFields(vararg fields: String): Boolean {
        return fields.all { it.isNotEmpty() }
    }
}