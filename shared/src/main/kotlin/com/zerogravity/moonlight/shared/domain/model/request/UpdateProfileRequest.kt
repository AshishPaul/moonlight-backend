package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val givenName: String?,
    val familyName: String?,
    val profilePictureUrl: String?
)
