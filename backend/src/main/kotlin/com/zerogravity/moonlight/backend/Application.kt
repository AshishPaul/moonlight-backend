package com.zerogravity.moonlight.backend

import com.zerogravity.moonlight.backend.domain.config.Config
import com.zerogravity.moonlight.backend.domain.util.extractConfig
import com.zerogravity.moonlight.backend.plugins.configureAuthentication
import com.zerogravity.moonlight.backend.plugins.configureKoin
import com.zerogravity.moonlight.backend.plugins.configureMonitoring
import com.zerogravity.moonlight.backend.plugins.configureRouting
import com.zerogravity.moonlight.backend.plugins.configureSerialization
import com.zerogravity.moonlight.backend.plugins.configureStatusPages
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.Application
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.dsl.module

fun main(args: Array<String>) {
    val environment = System.getenv()["ENVIRONMENT"] ?: handleDefaultEnvironment()

    val config = extractConfig(environment, HoconApplicationConfig(ConfigFactory.load()))

    embeddedServer(Netty, port = config.webPort) {
        println("Starting instance in ${config.webHost}:${config.webPort}")
        module {
            module(config)
        }
    }.start(wait = true)
}

fun Application.module(config: Config) {
    configureKoin(config)
    configureMonitoring()
    configureSerialization()
    configureStatusPages()
    configureAuthentication()
    configureRouting()
}


fun handleDefaultEnvironment(): String {
    println("Falling back to default environment 'dev'")
    return "dev"
}
