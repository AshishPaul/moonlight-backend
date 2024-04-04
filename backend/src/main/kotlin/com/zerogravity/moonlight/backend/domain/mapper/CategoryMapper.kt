package com.zerogravity.moonlight.backend.domain.mapper

import com.zerogravity.moonlight.backend.data.model.CategoryDbModel
import com.zerogravity.moonlight.shared.domain.model.dto.Category
import com.zerogravity.moonlight.shared.domain.model.request.CategoryRequest
import org.bson.types.ObjectId

fun CategoryRequest.dto() = Category(
    id = ObjectId().toHexString(),
    title = title,
    description = description
)

fun CategoryDbModel.dto() = Category(
    id = id.toString(),
    title = title,
    description = description
)