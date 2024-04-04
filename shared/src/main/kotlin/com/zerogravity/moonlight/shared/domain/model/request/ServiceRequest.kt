package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
class ServiceRequest(
    val categoryId: String,
    val title: String,
    val description: String,
    val price: Double
)

