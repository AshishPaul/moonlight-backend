package com.zerogravity.moonlight.backend.domain.util

import com.zerogravity.moonlight.backend.domain.config.Config
import com.zerogravity.moonlight.backend.domain.modules.auth.UserPrincipal
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.authentication
import io.ktor.server.config.HoconApplicationConfig

val ApplicationCall.userResponse get() = authentication.principal<UserPrincipal>()?.user!!

fun extractConfig(environment: String, hoconConfig: HoconApplicationConfig): Config {
    val hoconEnvironment = hoconConfig.config("ktor.deployment.$environment")
    val hoconMongo = hoconConfig.config("ktor.deployment.mongo")
    return Config(
        environment,
        hoconEnvironment.property("web.host").getString(),
        Integer.parseInt(hoconEnvironment.property("web.port").getString()),
        hoconEnvironment.property("database.host").getString(),
        hoconEnvironment.property("database.port").getString(),
        hoconMongo.property("uri").getString(),
        hoconMongo.property("dbName").getString()
    )
}