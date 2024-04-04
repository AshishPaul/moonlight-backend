package com.zerogravity.moonlight.shared.domain.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val givenName: String,
    val familyName: String,
    val email: String,
    val phoneNumber: String,
    val profilePictureUrl: String
)

