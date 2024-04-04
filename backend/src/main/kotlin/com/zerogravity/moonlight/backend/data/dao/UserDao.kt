package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.shared.domain.model.dto.User
import com.zerogravity.moonlight.shared.domain.model.request.UpdateProfileRequest

interface UserDao {
    suspend fun createUser(userDto: User, encryptedPassword: String): Boolean
    suspend fun updateUser(userId: String, updateProfileRequest: UpdateProfileRequest): Boolean
    suspend fun deleteUser(userId: String): Boolean
    suspend fun getUserById(userId: String): User?
    suspend fun getUserByEmail(emailValue: String): User?
    suspend fun getPassword(emailValue: String): String?
}



