package com.zerogravity.moonlight.shared.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Tokens(
    val accessToken: String,
    val refreshToken: String
)