package com.zerogravity.moonlight.shared.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse(val success: Boolean, val message: String)