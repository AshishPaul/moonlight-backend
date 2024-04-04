package com.zerogravity.moonlight.shared.domain.model.dto

import kotlinx.serialization.Serializable

@Serializable
class Category(
    val id: String,
    val title: String,
    val description: String
)