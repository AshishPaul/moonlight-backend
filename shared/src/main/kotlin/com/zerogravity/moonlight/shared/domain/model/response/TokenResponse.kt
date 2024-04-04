package com.zerogravity.moonlight.shared.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val tokens: Tokens
)