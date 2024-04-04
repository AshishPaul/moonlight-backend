package com.zerogravity.moonlight.backend.data.dao

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineDispatcherDao(
    private val databaseDispatcher: CoroutineDispatcher
) {
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        withContext(databaseDispatcher) { block() }
}