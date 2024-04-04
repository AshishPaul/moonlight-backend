package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginWithCredentialRequest(
    val email: String,
    val password: String
)