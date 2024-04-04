package com.zerogravity.moonlight.shared.domain.error

data class FailedToUpdateResourceException(override val message: String = "Failed to update resource") : Exception()