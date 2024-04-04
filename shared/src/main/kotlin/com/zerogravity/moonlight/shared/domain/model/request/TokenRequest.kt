package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    val token: String
)