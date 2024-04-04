package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.shared.domain.model.dto.Service

interface ServiceDao {
    suspend fun createService(service: Service): Boolean
    suspend fun getAllServices(): List<Service>
    suspend fun getServiceById(serviceId: String): Service?
    suspend fun getServicesByCategory(categoryId: String): List<Service>
}