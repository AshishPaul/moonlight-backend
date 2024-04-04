package com.zerogravity.moonlight.shared.domain.error

data class UserNotFoundException(override val message: String = "The requested user is not found") : Exception()