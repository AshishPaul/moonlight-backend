package com.zerogravity.moonlight.backend.domain.mapper

import com.zerogravity.moonlight.backend.data.model.UserDbModel
import com.zerogravity.moonlight.shared.domain.model.dto.User
import com.zerogravity.moonlight.shared.domain.model.request.UserRequest
import org.bson.types.ObjectId

fun UserRequest.dto() = User(
    id = ObjectId().toHexString(),
    givenName = this.givenName,
    familyName = this.familyName,
    email = this.email,
    profilePictureUrl = this.profilePicUrl,
    phoneNumber = this.phoneNumber,
)

fun UserDbModel.dto() = User(
    id = this.id.toString(),
    givenName = this.givenName,
    familyName = this.familyName,
    email = this.email,
    phoneNumber = this.phoneNumber,
    profilePictureUrl = this.profilePictureUrl
)