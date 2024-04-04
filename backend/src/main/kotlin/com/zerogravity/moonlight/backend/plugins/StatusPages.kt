package com.zerogravity.moonlight.backend.plugins

import com.zerogravity.moonlight.shared.domain.error.AuthenticationException
import com.zerogravity.moonlight.shared.domain.error.AuthorizationException
import com.zerogravity.moonlight.shared.domain.error.FailedToCreateResourceException
import com.zerogravity.moonlight.shared.domain.error.FailedToUpdateResourceException
import com.zerogravity.moonlight.shared.domain.error.MissingArgumentException
import com.zerogravity.moonlight.shared.domain.error.NoContentFound
import com.zerogravity.moonlight.shared.domain.error.UserAlreadyExistsException
import com.zerogravity.moonlight.shared.domain.error.UserNotFoundException
import com.zerogravity.moonlight.shared.domain.model.response.CommonResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondToAppError(cause)
        }
    }
}

suspend fun ApplicationCall.respondToAppError(throwable: Throwable) {

    val message = throwable.message ?: throwable.stackTraceToString()
    val statusCode = when (throwable) {
        is AuthenticationException -> HttpStatusCode.Unauthorized
        is AuthorizationException -> HttpStatusCode.Forbidden
        is FailedToCreateResourceException -> HttpStatusCode.InternalServerError
        is FailedToUpdateResourceException -> HttpStatusCode.InternalServerError
        is UserAlreadyExistsException -> HttpStatusCode.BadRequest
        is UserNotFoundException -> HttpStatusCode.NotFound
        is NoContentFound -> HttpStatusCode.NoContent
        is MissingArgumentException -> HttpStatusCode.BadRequest
        is IllegalArgumentException -> HttpStatusCode.BadRequest
        is UnknownError -> HttpStatusCode.InternalServerError
        else -> HttpStatusCode.InternalServerError
    }
    this.respond(statusCode, CommonResponse(false, message))
}


