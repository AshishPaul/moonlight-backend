package com.zerogravity.moonlight.backend.domain.modules.services

import com.zerogravity.moonlight.backend.data.dao.CategoryDao
import com.zerogravity.moonlight.backend.data.dao.ServiceDao
import com.zerogravity.moonlight.backend.domain.ApiResponseMapper
import com.zerogravity.moonlight.backend.domain.mapper.dto
import com.zerogravity.moonlight.shared.domain.error.FailedToCreateResourceException
import com.zerogravity.moonlight.shared.domain.model.request.CategoryRequest
import com.zerogravity.moonlight.shared.domain.model.request.ServiceRequest
import com.zerogravity.moonlight.shared.domain.model.response.CategoryListResponse
import com.zerogravity.moonlight.shared.domain.model.response.CommonResponse
import com.zerogravity.moonlight.shared.domain.model.response.ServiceListResponse
import com.zerogravity.moonlight.shared.domain.model.response.toListResponse

interface ServiceController {
    suspend fun getAllCategories(): CategoryListResponse
    suspend fun getAllServices(): ServiceListResponse
    suspend fun getServices(categoryId: String): ServiceListResponse
    suspend fun createCategory(categoryRequest: CategoryRequest): CommonResponse
    suspend fun createService(serviceRequest: ServiceRequest): CommonResponse
}

class ServiceControllerImpl(
    private val servicesDao: ServiceDao,
    private val categoryDao: CategoryDao,
) : ServiceController, ApiResponseMapper() {

    override suspend fun getAllCategories() = nullCatching {
        categoryDao.getAllCategories().toListResponse()
    }

    override suspend fun getAllServices() = nullCatching {
        servicesDao.getAllServices().toListResponse()
    }

    override suspend fun getServices(categoryId: String) = nullCatching {
        servicesDao.getServicesByCategory(categoryId).toListResponse()
    }

    override suspend fun createCategory(categoryRequest: CategoryRequest) = nullCatching {
        try {
            val result = categoryDao.createCategory(categoryRequest.dto())
            if (result) {
                CommonResponse(true, "Service created successfully")
            } else {
                throw FailedToCreateResourceException()
            }
        } catch (e: Throwable) {
            throw FailedToCreateResourceException("Failed to create the category: $categoryRequest")
        }
    }

    override suspend fun createService(serviceRequest: ServiceRequest) = nullCatching {
        try {
            val result = servicesDao.createService(serviceRequest.dto())
            if (result) {
                CommonResponse(true, "Service created successfully")
            } else {
                throw FailedToCreateResourceException()
            }
        } catch (e: Throwable) {
            throw FailedToCreateResourceException("Failed to create the service: $serviceRequest")
        }
    }
}