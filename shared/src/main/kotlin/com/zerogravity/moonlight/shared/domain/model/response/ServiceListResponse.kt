package com.zerogravity.moonlight.shared.domain.model.response

import com.zerogravity.moonlight.shared.domain.DataListSerializer
import com.zerogravity.moonlight.shared.domain.model.dto.Service
import kotlinx.serialization.Serializable

@Serializable
data class ServiceListResponse(
    @Serializable(with = DataListSerializer::class) val items: List<Service>
)

fun List<Service>.toListResponse() = ServiceListResponse(
    items = this
)