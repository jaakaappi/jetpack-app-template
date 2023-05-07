package com.example.template.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example_row")
data class ExampleModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)