package com.zerogravity.moonlight.shared.domain.error

data class FailedToCreateResourceException(override val message: String = "Failed to create resource") : Exception()