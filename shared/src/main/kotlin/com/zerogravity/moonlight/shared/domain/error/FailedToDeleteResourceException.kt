package com.zerogravity.moonlight.shared.domain.error

data class FailedToDeleteResourceException(override val message: String = "Failed to delete resource") : Exception()