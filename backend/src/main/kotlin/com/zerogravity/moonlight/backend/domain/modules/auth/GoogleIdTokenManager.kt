package com.zerogravity.moonlight.backend.domain.modules.auth

import com.zerogravity.moonlight.shared.domain.model.dto.User
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory

private const val AUDIENCE =
    "459857664269-i83jet625liripnfd5hj3flf93o93oje.apps.googleusercontent.com"
private const val ISSUER = "https://accounts.google.com"

object GoogleIdTokenManager {

    fun verifyIdToken(idToken: String): GoogleIdToken? {
        val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(listOf(AUDIENCE))
            .setIssuer(ISSUER)
            .build()
        return verifier.verify(idToken)
    }

    fun getUserFromIdToken(it: GoogleIdToken): User {
        TODO("Not yet implemented")
    }
}