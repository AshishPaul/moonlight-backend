package com.zerogravity.moonlight.backend.di

import com.zerogravity.moonlight.backend.data.dao.CategoryDao
import com.zerogravity.moonlight.backend.data.dao.CategoryDaoImpl
import com.zerogravity.moonlight.backend.data.dao.ServiceDao
import com.zerogravity.moonlight.backend.data.dao.ServiceDaoImpl
import com.zerogravity.moonlight.backend.data.dao.UserDao
import com.zerogravity.moonlight.backend.data.dao.UserDaoImpl
import com.zerogravity.moonlight.backend.data.model.CategoryDbModel
import com.zerogravity.moonlight.backend.data.model.ServiceDbModel
import com.zerogravity.moonlight.backend.data.model.UserDbModel
import com.zerogravity.moonlight.backend.domain.config.Config
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

fun dbModule(config: Config) = module {

    single<MongoClient> {
        MongoClient.create(config.mongoUri)
    }
    single {
        get<MongoClient>().getDatabase(config.mongoDatabaseName)
    }

    single<UserDao> {
        UserDaoImpl(
            get<MongoDatabase>().getCollection<UserDbModel>("user"),
            Dispatchers.IO
        )
    }
    single<ServiceDao> {
        ServiceDaoImpl(
            get<MongoDatabase>().getCollection<ServiceDbModel>("service"),
            Dispatchers.IO
        )
    }
    single<CategoryDao> {
        CategoryDaoImpl(
            get<MongoDatabase>().getCollection<CategoryDbModel>("category"),
            Dispatchers.IO
        )
    }
}