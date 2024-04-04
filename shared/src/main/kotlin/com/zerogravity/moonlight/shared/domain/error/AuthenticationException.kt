package com.zerogravity.moonlight.shared.domain.error

data class AuthenticationException(override val message: String = "Authentication failed") :
    Exception()

data class AuthorizationException(override val message: String = "You are not authorised to use this service") :
    Exception()