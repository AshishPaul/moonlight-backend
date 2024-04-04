package com.zerogravity.moonlight.shared.domain.model.response

import com.zerogravity.moonlight.shared.domain.model.dto.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val givenName: String,
    val familyName: String,
    val email: String,
    val phoneNumber: String,
    val profilePictureUrl: String
)

fun User.toUserResponse() = UserResponse(
    id = this.id,
    givenName = this.givenName,
    familyName = this.familyName,
    email = this.email,
    phoneNumber = this.phoneNumber,
    profilePictureUrl = this.profilePictureUrl
)