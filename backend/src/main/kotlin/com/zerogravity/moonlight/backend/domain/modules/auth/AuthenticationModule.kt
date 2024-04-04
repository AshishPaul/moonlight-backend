package com.zerogravity.moonlight.backend.domain.modules.auth

import com.zerogravity.moonlight.backend.domain.modules.user.UserController
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.jwt.jwt

fun AuthenticationConfig.authenticationModule(
    userController: UserController,
    tokenProvider: TokenProvider
) {
    jwt("jwt") {
        verifier(tokenProvider.getVerifier())
        realm = "ktor.io"
        validate { credential ->
            credential.payload.getClaim("id").asString()?.let { userId ->
                // do database query to find Principal subclass
                userController.getUserById(userId).let {
                    it?.let { UserPrincipal(it) }
                }
            }
        }
    }
}