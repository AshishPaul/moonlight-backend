package com.zerogravity.moonlight.backend.domain.modules.registration

import com.zerogravity.moonlight.backend.data.dao.UserDao
import com.zerogravity.moonlight.backend.domain.ApiResponseMapper
import com.zerogravity.moonlight.backend.domain.mapper.dto
import com.zerogravity.moonlight.backend.domain.modules.auth.GoogleIdTokenManager
import com.zerogravity.moonlight.backend.domain.modules.auth.TokenProvider
import com.zerogravity.moonlight.backend.domain.util.PasswordManager
import com.zerogravity.moonlight.shared.domain.error.AuthenticationException
import com.zerogravity.moonlight.shared.domain.error.FailedToCreateResourceException
import com.zerogravity.moonlight.shared.domain.error.UserAlreadyExistsException
import com.zerogravity.moonlight.shared.domain.model.request.LoginWithCredentialRequest
import com.zerogravity.moonlight.shared.domain.model.request.TokenRequest
import com.zerogravity.moonlight.shared.domain.model.request.UpdateProfileRequest
import com.zerogravity.moonlight.shared.domain.model.request.UserRequest
import com.zerogravity.moonlight.shared.domain.model.response.CommonResponse
import com.zerogravity.moonlight.shared.domain.model.response.TokenResponse

interface RegistrationController {
    suspend fun signUpUser(userRequest: UserRequest): CommonResponse
    suspend fun authenticateWithCredential(request: LoginWithCredentialRequest): TokenResponse
    suspend fun authenticateWithIdToken(request: TokenRequest): TokenResponse
    suspend fun refreshToken(request: TokenRequest): TokenResponse
}

class RegistrationControllerImpl(
    private val usersDao: UserDao,
    private val passwordManager: PasswordManager,
    private val tokenProvider: TokenProvider,
) : RegistrationController, ApiResponseMapper() {

    override suspend fun signUpUser(userRequest: UserRequest) = nullCatching {
        usersDao.getUserByEmail(userRequest.email)?.let {
            throw UserAlreadyExistsException()
        }
        try {
            usersDao.createUser(
                userRequest.dto(),
                passwordManager.encryptPassword(userRequest.password)
            )
            CommonResponse(true, "Sign-up successful")
        } catch (e: Throwable) {
            throw FailedToCreateResourceException("Error while creating user")
        }
    }

    override suspend fun authenticateWithCredential(request: LoginWithCredentialRequest) =
        nullCatching {
            usersDao.getUserByEmail(request.email)?.let { user ->
                if (passwordManager.validatePassword(
                        request.password,
                        usersDao.getPassword(request.email)
                    )
                ) {
                    val tokens = tokenProvider.createTokens(user)
                    TokenResponse(tokens)
                } else {
                    throw AuthenticationException("Invalid credentials")
                }
            } ?: throw AuthenticationException("Invalid credentials")
        }

    override suspend fun authenticateWithIdToken(request: TokenRequest) = nullCatching {
        val verifiedIdToken = GoogleIdTokenManager.verifyIdToken(request.token)
        verifiedIdToken?.let {
            val userDto = GoogleIdTokenManager.getUserFromIdToken(it)
            val userInDb = usersDao.getUserByEmail(userDto.email)
            if (userInDb == null) {
                try {
                    usersDao.createUser(userDto, "")
                } catch (e: Throwable) {
                    throw FailedToCreateResourceException("Error while creating user")
                }
            } else {
                usersDao.updateUser(
                    userInDb.id!!,
                    UpdateProfileRequest(
                        userDto.givenName,
                        userDto.familyName,
                        userDto.profilePictureUrl
                    )
                )
            }
            val tokens = tokenProvider.createTokens(usersDao.getUserByEmail(userDto.email)!!)
            TokenResponse(tokens)

        } ?: throw AuthenticationException("Invalid idToken")
    }

    override suspend fun refreshToken(request: TokenRequest) = nullCatching {
        tokenProvider.verifyToken(request.token)?.let {
            usersDao.getUserById(it)?.let { user ->
                val tokens = tokenProvider.createTokens(user)
                TokenResponse(tokens)
            } ?: throw AuthenticationException("Invalid token")
        } ?: throw AuthenticationException("Invalid token")
    }
}

