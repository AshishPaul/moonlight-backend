package com.zerogravity.moonlight.shared.domain.model.dto

import kotlinx.serialization.Serializable

@Serializable
class Service(
    val id: String,
    val categoryId: String,
    val title: String,
    val description: String,
    val price: Double
)