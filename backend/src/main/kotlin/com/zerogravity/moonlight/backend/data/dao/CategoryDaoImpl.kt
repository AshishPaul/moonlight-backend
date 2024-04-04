package com.zerogravity.moonlight.backend.data.dao

import com.zerogravity.moonlight.backend.data.model.CategoryDbModel
import com.zerogravity.moonlight.backend.domain.mapper.dto
import com.zerogravity.moonlight.shared.domain.model.dto.Category
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class CategoryDaoImpl(
    private val categoryCollection: MongoCollection<CategoryDbModel>,
    databaseDispatcher: CoroutineDispatcher
) : CategoryDao, CoroutineDispatcherDao(databaseDispatcher) {

    override suspend fun createCategory(category: Category): Boolean = dbQuery {
        try {
            categoryCollection.insertOne(
                CategoryDbModel(
                    id = ObjectId(category.id),
                    title = category.title,
                    description = category.description,
                    dateCreated = System.currentTimeMillis(),
                    dateUpdated = System.currentTimeMillis()
                )
            ).wasAcknowledged()
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
            throw e
        }
    }

    override suspend fun deleteCategory(categoryId: String): Boolean = dbQuery {
        try {
            categoryCollection.deleteOne(
                Filters.eq(CategoryDbModel::id.name, categoryId)
            ).wasAcknowledged()
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
            throw e
        }
    }

    override suspend fun getAllCategories(): List<Category> = dbQuery {
        categoryCollection.withDocumentClass<CategoryDbModel>()
            .find()
            .map(CategoryDbModel::dto)
            .toList()
    }

    override suspend fun getCategoryById(categoryId: String): Category? = dbQuery {
        categoryCollection.find(Filters.eq("_id", categoryId))
            .firstOrNull()
            ?.dto()
    }
}