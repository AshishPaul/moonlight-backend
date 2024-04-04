package com.zerogravity.moonlight.backend.domain.config

class Config(
    val env: String,
    val webHost: String,
    val webPort: Int,
    val databaseHost: String,
    val databasePort: String,
    val mongoUri: String,
    val mongoDatabaseName: String
) {

    companion object {
        const val DATABASE_NAME: String = "moonlightdb"
        const val DATABASE_USERNAME: String = "moonlightadmin"
        const val DATABASE_PASSWORD: String = "MoonlightAdmin"
    }
}