package com.zerogravity.moonlight.backend.domain.modules.homePage

import com.zerogravity.moonlight.shared.domain.ApiRoutes
import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.indexRouting() {

    val indexPage = javaClass.getResource("/index.html").readText()

    get(ApiRoutes.Endpoint.Root.path) {
        call.respondText(indexPage, ContentType.Text.Html)
    }
}
