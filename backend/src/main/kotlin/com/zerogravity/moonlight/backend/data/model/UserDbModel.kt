package com.zerogravity.moonlight.backend.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class UserDbModel(
    @SerialName("_id") // Use instead of @BsonId
    @Contextual val id: ObjectId,
    val givenName: String,
    val familyName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val profilePictureUrl: String,
    val dateCreated: Long,
    val dateUpdated: Long
)