package com.zerogravity.moonlight.backend.plugins

import com.zerogravity.moonlight.backend.domain.modules.auth.TokenProvider
import com.zerogravity.moonlight.backend.domain.modules.auth.authenticationModule
import com.zerogravity.moonlight.backend.domain.modules.user.UserController
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import org.koin.ktor.ext.inject

fun Application.configureAuthentication() {
    val tokenProvider by inject<TokenProvider>()
    val userController by inject<UserController>()

    install(Authentication) {
        authenticationModule(userController, tokenProvider)
    }
}