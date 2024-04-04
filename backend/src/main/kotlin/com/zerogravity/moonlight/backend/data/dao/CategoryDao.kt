package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.shared.domain.model.dto.Category

interface CategoryDao {
    suspend fun createCategory(category: Category): Boolean
    suspend fun deleteCategory(categoryId: String): Boolean

    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryById(categoryId: String): Category?
}
