package com.zerogravity.moonlight.backend.domain.modules.user

import com.zerogravity.moonlight.backend.domain.util.userResponse
import com.zerogravity.moonlight.shared.domain.ApiRoutes
import com.zerogravity.moonlight.shared.domain.model.request.UpdateProfileRequest
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import org.koin.ktor.ext.inject

fun Route.userModule() {

    val controller by inject<UserController>()

    get(ApiRoutes.Endpoint.MyProfile.path) {
        call.respond(call.userResponse)
    }

    put(ApiRoutes.Endpoint.UpdateProfile.path) {
        val putUser = call.receive<UpdateProfileRequest>()
        call.respond(controller.updateProfile(call.userResponse.id, putUser))
    }

    delete(ApiRoutes.Endpoint.User.path) {
        call.respond(controller.removeUser(call.userResponse.id))
    }
}
