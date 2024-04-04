package com.zerogravity.moonlight.backend.domain.modules.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.zerogravity.moonlight.shared.domain.model.dto.User
import com.zerogravity.moonlight.shared.domain.model.response.Tokens
import java.util.Date

interface TokenProvider {
    fun getVerifier(): JWTVerifier
    fun createTokens(user: User): Tokens
    fun verifyToken(token: String): String?
}

class JwtTokenProvider : TokenProvider {

    private val algorithm = Algorithm.HMAC512(secret)

    private val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    override fun getVerifier(): JWTVerifier {
        return verifier
    }

    override fun verifyToken(token: String): String? {
        return verifier.verify(token).claims["id"]?.asString()
    }

    /**
     * Produce token and refresh token for this combination of User and Account
     */
    override fun createTokens(user: User) = Tokens(
        createToken(user, getTokenExpiration()),
        createToken(user, getTokenExpiration(refreshValidityInMs))
    )

    private fun createToken(userDto: User, expiration: Date) =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("id", userDto.id)
            .withClaim("givenName", userDto.givenName)
            .withClaim("familyName", userDto.familyName)
            .withClaim("profilePictureUrl", userDto.profilePictureUrl)
            .withClaim("phoneNumber", userDto.phoneNumber)
            .withClaim("email", userDto.email)
            .withExpiresAt(expiration).sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun getTokenExpiration(validity: Long = validityInMs) =
        Date(System.currentTimeMillis() + validity)

    companion object {
        //TODO The issuer and secret shouldd be moded to somewhere secure
        private const val secret = "moonlight"
        private const val issuer = "moonlight-backend"

        private const val validityInMs: Long = 3600000L * 24L // 24h
        private const val refreshValidityInMs: Long = 3600000L * 24L * 30L // 30 days
    }
}

