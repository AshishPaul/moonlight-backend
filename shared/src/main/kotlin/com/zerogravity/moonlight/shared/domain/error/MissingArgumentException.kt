package com.zerogravity.moonlight.shared.domain.error

data class MissingArgumentException(override val message: String = "Missing argument") : Exception()