package com.zerogravity.moonlight.backend.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class CategoryDbModel(
    @SerialName("_id") // Use instead of @BsonId
    @Contextual val id: ObjectId,
    val title: String,
    val description: String,
    val dateCreated: Long,
    val dateUpdated: Long
)