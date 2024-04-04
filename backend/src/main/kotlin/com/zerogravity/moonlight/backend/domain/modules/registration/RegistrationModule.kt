package com.zerogravity.moonlight.backend.domain.modules.registration

import com.zerogravity.moonlight.shared.domain.ApiRoutes
import com.zerogravity.moonlight.shared.domain.model.request.LoginWithCredentialRequest
import com.zerogravity.moonlight.shared.domain.model.request.TokenRequest
import com.zerogravity.moonlight.shared.domain.model.request.UserRequest
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.registrationModule() {

    val unauthenticatedController by inject<RegistrationController>()

    post(ApiRoutes.Endpoint.SignUp.path) {
        call.application.log.info("POST Route called : ${ApiRoutes.Endpoint.SignUp.path}")
        val request = call.receive<UserRequest>()
        call.respond(unauthenticatedController.signUpUser(request))
    }

    post(ApiRoutes.Endpoint.AuthWithCredential.path) {
        val request = call.receive<LoginWithCredentialRequest>()
        call.respond(unauthenticatedController.authenticateWithCredential(request))
    }

    post(ApiRoutes.Endpoint.AuthWithToken.path) {
        val request = call.receive<TokenRequest>()
        call.respond(unauthenticatedController.authenticateWithIdToken(request))
    }

    //TODO refine authenticated or not
    post(ApiRoutes.Endpoint.RefreshToken.path) {
        val request = call.receive<TokenRequest>()
        call.respond(unauthenticatedController.refreshToken(request))
    }
}