package com.zerogravity.moonlight.shared.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
class CategoryRequest(
    val title: String,
    val description: String
)
