package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.backend.data.model.UserDbModel
import com.zerogravity.moonlight.backend.domain.mapper.dto
import com.zerogravity.moonlight.shared.domain.model.dto.User
import com.zerogravity.moonlight.shared.domain.model.request.UpdateProfileRequest
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId

class UserDaoImpl(
    private val userCollection: MongoCollection<UserDbModel>,
    databaseDispatcher: CoroutineDispatcher
) : UserDao, CoroutineDispatcherDao(databaseDispatcher) {

    override suspend fun createUser(userDto: User, encryptedPassword: String): Boolean =
        dbQuery {
            try {
                userCollection.insertOne(
                    UserDbModel(
                        id = ObjectId(userDto.id),
                        givenName = userDto.givenName,
                        familyName = userDto.familyName,
                        email = userDto.email,
                        phoneNumber = userDto.phoneNumber,
                        password = encryptedPassword,
                        profilePictureUrl = userDto.profilePictureUrl,
                        dateCreated = System.currentTimeMillis(),
                        dateUpdated = System.currentTimeMillis()
                    )
                ).wasAcknowledged()
            } catch (e: MongoException) {
                System.err.println("Unable to insert due to an error: $e")
                throw e
            }
        }

    override suspend fun updateUser(
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ) = dbQuery {
        try {
            val query = Filters.eq("_id", ObjectId(userId))
            val updates = Updates.combine(
                Updates.set(UserDbModel::givenName.name, updateProfileRequest.givenName),
                Updates.set(UserDbModel::familyName.name, updateProfileRequest.familyName),
                Updates.set(UserDbModel::profilePictureUrl.name, updateProfileRequest.profilePictureUrl)
            )
            val options = UpdateOptions().upsert(true)
            val result =
                userCollection.updateOne(query, updates, options)

            result.wasAcknowledged()
        } catch (e: MongoException) {
            System.err.println("Unable to update due to an error: $e")
            throw e
        }
    }

    override suspend fun deleteUser(userId: String) = dbQuery {
        try {
            val result = userCollection.deleteOne(Filters.eq("_id", ObjectId(userId)))
            result.wasAcknowledged()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
            throw e
        }
    }

    override suspend fun getUserById(userId: String) = dbQuery {
        userCollection.find(Filters.eq("_id", ObjectId(userId)))
            .firstOrNull()
            ?.dto()
    }

    override suspend fun getUserByEmail(emailValue: String) = dbQuery {
        userCollection.find(Filters.eq(UserDbModel::email.name, emailValue))
            .firstOrNull()
            ?.dto()
    }

    override suspend fun getPassword(emailValue: String): String? = dbQuery {
        userCollection.find(Filters.eq(UserDbModel::email.name, emailValue))
            .firstOrNull()
            ?.password
    }
}