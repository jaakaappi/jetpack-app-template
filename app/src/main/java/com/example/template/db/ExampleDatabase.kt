package com.example.template.db

import androidx.room.RoomDatabase
import com.example.template.db.models.ExampleModel
import javax.inject.Singleton

@Singleton
@androidx.room.Database(version = 1, entities = [ExampleModel::class])
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun getExampleDao(): ExampleDao
}