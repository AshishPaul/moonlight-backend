package com.zerogravity.moonlight.backend.domain.modules.user

import com.zerogravity.moonlight.backend.data.dao.UserDao
import com.zerogravity.moonlight.backend.domain.ApiResponseMapper
import com.zerogravity.moonlight.shared.domain.error.FailedToDeleteResourceException
import com.zerogravity.moonlight.shared.domain.error.FailedToUpdateResourceException
import com.zerogravity.moonlight.shared.domain.error.UserNotFoundException
import com.zerogravity.moonlight.shared.domain.model.request.UpdateProfileRequest
import com.zerogravity.moonlight.shared.domain.model.response.CommonResponse
import com.zerogravity.moonlight.shared.domain.model.response.UserResponse
import com.zerogravity.moonlight.shared.domain.model.response.toUserResponse

interface UserController {

    suspend fun updateProfile(
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ): CommonResponse

    suspend fun removeUser(userId: String): CommonResponse

    suspend fun getUserById(userId: String): UserResponse?
}

class UserControllerImpl(
    private val userDao: UserDao
) : UserController, ApiResponseMapper() {

    override suspend fun updateProfile(
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ): CommonResponse {
        val user = userDao.getUserById(userId)
        if (user != null) {
            val updateResult = userDao.updateUser(userId, updateProfileRequest)
            if (updateResult) {
                return CommonResponse(true, "Profile updated successfully.")
            } else {
                throw FailedToUpdateResourceException()
            }
        } else {
            throw UserNotFoundException()
        }
    }

    override suspend fun removeUser(userId: String) = nullCatching {
        val result = userDao.deleteUser(userId)
        if (result) {
            CommonResponse(true, "User deleted successfully.")
        } else {
            throw FailedToDeleteResourceException()
        }
    }

    override suspend fun getUserById(userId: String) = nullCatching {
        userDao.getUserById(userId)?.toUserResponse()
    }
}