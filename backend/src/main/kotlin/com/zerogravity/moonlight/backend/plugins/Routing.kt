package com.zerogravity.moonlight.backend.plugins

import com.zerogravity.moonlight.backend.domain.modules.homePage.indexRouting
import com.zerogravity.moonlight.backend.domain.modules.metrics.metrics
import com.zerogravity.moonlight.backend.domain.modules.registration.registrationModule
import com.zerogravity.moonlight.backend.domain.modules.services.serviceModule
import com.zerogravity.moonlight.backend.domain.modules.user.userModule
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    routing {
        indexRouting()
        registrationModule()
        serviceModule()
        authenticate("jwt") {
            userModule()
        }
        // We add our metrics endpoint to route definition
        metrics()
    }
}
