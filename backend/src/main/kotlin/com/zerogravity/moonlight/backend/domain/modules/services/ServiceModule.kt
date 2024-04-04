package com.zerogravity.moonlight.backend.domain.modules.services

import com.zerogravity.moonlight.shared.domain.ApiRoutes
import com.zerogravity.moonlight.shared.domain.model.request.CategoryRequest
import com.zerogravity.moonlight.shared.domain.model.request.ServiceRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Routing.serviceModule() {

    val serviceController by inject<ServiceController>()

    route(ApiRoutes.Endpoint.Category.path) {
        get {
            call.application.log.info("GET Route called : /category")
            call.respond(serviceController.getAllCategories())
        }

        post {
            call.application.environment.log.info("POST Route called : /category")
            val newCategory = call.receive<CategoryRequest>()
            call.respond(serviceController.createCategory(newCategory))
        }
    }

    route(ApiRoutes.Endpoint.ServiceByCategoryId.path) {
        get {
            call.application.environment.log.info("GET Route called : /category/{categoryId?}/service")
            val categoryId = call.parameters["categoryId"] ?: return@get call.respondText(
                "Missing categoryId", status = HttpStatusCode.BadRequest
            )
            call.respond(serviceController.getServices(categoryId))
        }
    }

    route(ApiRoutes.Endpoint.Service.path) {
        get {
            call.application.environment.log.info("GET Route called : /service")
            call.respond(serviceController.getAllServices())
        }

        post {
            call.application.environment.log.info("POST Route called : /category/{categoryId?}/service")
            val newService = call.receive<ServiceRequest>()
            call.respond(serviceController.createService(newService))
        }
    }
}
