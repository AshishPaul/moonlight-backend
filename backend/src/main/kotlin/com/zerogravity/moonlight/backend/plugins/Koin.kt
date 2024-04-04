package com.zerogravity.moonlight.backend.plugins

import com.zerogravity.moonlight.backend.di.configModule
import com.zerogravity.moonlight.backend.di.controllerModule
import com.zerogravity.moonlight.backend.di.dbModule
import com.zerogravity.moonlight.backend.domain.config.Config
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(config: Config) {
    install(Koin) {
        modules(
            configModule(config),
//            exposedDbModule(),
            dbModule(config),
            controllerModule()
        )
    }
}