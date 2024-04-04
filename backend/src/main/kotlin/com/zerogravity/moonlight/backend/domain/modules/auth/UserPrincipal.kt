package com.zerogravity.moonlight.backend.domain.modules.auth

import com.zerogravity.moonlight.shared.domain.model.response.UserResponse
import io.ktor.server.auth.Principal

data class UserPrincipal(val user: UserResponse) : Principal