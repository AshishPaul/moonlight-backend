package com.zerogravity.moonlight.shared.domain.model.response

import com.zerogravity.moonlight.shared.domain.DataListSerializer
import com.zerogravity.moonlight.shared.domain.model.dto.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryListResponse(
    @Serializable(with = DataListSerializer::class) val items: List<Category>
)

fun List<Category>.toListResponse() = CategoryListResponse(
    items = this
)