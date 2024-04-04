package com.zerogravity.moonlight.backend.domain.mapper

import com.zerogravity.moonlight.backend.data.model.ServiceDbModel
import com.zerogravity.moonlight.shared.domain.model.dto.Service
import com.zerogravity.moonlight.shared.domain.model.request.ServiceRequest
import org.bson.types.ObjectId

fun ServiceRequest.dto() = Service(
    id = ObjectId().toHexString(),
    categoryId = categoryId,
    title = title,
    description = description,
    price = price
)

fun ServiceDbModel.dto() = Service(
    id = id.toString(),
    categoryId = categoryId,
    title = title,
    description = description,
    price = price
)