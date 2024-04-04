package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.backend.data.model.ServiceDbModel
import com.zerogravity.moonlight.backend.domain.mapper.dto
import com.zerogravity.moonlight.shared.domain.model.dto.Service
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class ServiceDaoImpl(
    private val serviceCollection: MongoCollection<ServiceDbModel>,
    databaseDispatcher: CoroutineDispatcher
) : ServiceDao, CoroutineDispatcherDao(databaseDispatcher) {

    override suspend fun getAllServices(): List<Service> = dbQuery {
        serviceCollection.find()
            .map(ServiceDbModel::dto).toList()
    }

    override suspend fun getServiceById(serviceId: String): Service? = dbQuery {
        serviceCollection.find(
            Filters.eq("_id", serviceId)
        ).firstOrNull()?.dto()
    }

    override suspend fun getServicesByCategory(categoryId: String): List<Service> = dbQuery {
        serviceCollection.find(
            Filters.eq(ServiceDbModel::categoryId.name, categoryId)
        ).map(ServiceDbModel::dto).toList()
    }

    override suspend fun createService(service: Service): Boolean = dbQuery {
        try {
            serviceCollection.insertOne(
                ServiceDbModel(
                    id = ObjectId(),
                    categoryId = service.categoryId,
                    title = service.title,
                    description = service.description,
                    price = service.price,
                    dateCreated = System.currentTimeMillis(),
                    dateUpdated = System.currentTimeMillis()
                )
            ).wasAcknowledged()
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
            throw e
        }
    }
}