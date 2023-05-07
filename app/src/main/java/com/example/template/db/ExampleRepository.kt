package com.example.template.db

import com.example.template.db.models.ExampleModel
import kotlinx.coroutines.flow.Flow

class ExampleRepository(private val exampleDao: ExampleDao) {
    fun getAllExamples(): Flow<List<ExampleModel>> = exampleDao.getAll()
    suspend fun addExample(exampleModel: ExampleModel) = exampleDao.add(exampleModel)
    suspend fun deleteAllExamples() = exampleDao.deleteAll()
}