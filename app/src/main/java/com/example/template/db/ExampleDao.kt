package com.example.template.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.template.db.models.ExampleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {
    @Insert
    suspend fun add(example: ExampleModel)

    @Delete
    suspend fun delete(example: ExampleModel)

    @Query("DELETE FROM example_row")
    suspend fun deleteAll()


    @Query("SELECT * from example_row")
    fun getAll(): Flow<List<ExampleModel>>


    @Query("SELECT * from example_row where id = :id")
    suspend fun getById(id: Int): ExampleModel
}