package com.zerogravity.moonlight.shared.domain.error

data class UserAlreadyExistsException(override val message: String = "The user already exists") : Exception()