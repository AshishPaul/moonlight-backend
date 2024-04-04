package com.zerogravity.moonlight.backend.domain

abstract class ApiResponseMapper {
    suspend fun <T> nullCatching(block: suspend () -> T?): T {
        val result = block()
        if (result == null) {
            throw NullPointerException()
        } else {
            return result
        }
    }
}