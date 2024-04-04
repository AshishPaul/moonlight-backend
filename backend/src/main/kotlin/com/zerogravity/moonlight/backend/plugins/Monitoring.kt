package com.zerogravity.moonlight.backend.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.request.path
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.DEBUG
        filter { call -> call.request.path().startsWith("/") }
    }

//    install(MicrometerMetrics) {
//        registry = prometeusRegistry
//        meterBinders = listOf(
//            ClassLoaderMetrics(),
//            JvmMemoryMetrics(),
//            JvmGcMetrics(),
//            ProcessorMetrics(),
//            JvmThreadMetrics(),
//            FileDescriptorMetrics(),
//            UptimeMetrics()
//        )
//    }
}
