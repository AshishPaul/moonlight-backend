package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val givenName: String,
    val familyName: String,
    val profilePicUrl: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)

