package com.example.template

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.template.db.ExampleDao
import com.example.template.db.ExampleDatabase
import com.example.template.db.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TemplateModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        provider: Provider<ExampleDao>
    ): ExampleDatabase =
        Room.databaseBuilder(context, ExampleDatabase::class.java, "template_database")
            .addCallback(ExerciseCallback(provider)).build()

    @Provides
    fun provideExerciseDao(database: ExampleDatabase): ExampleDao = database.getExampleDao()


    @Provides
    @Singleton
    fun provideTemplateRepository(
        exampleDao: ExampleDao
    ): ExampleRepository = ExampleRepository(
        exampleDao
    )
}

class ExerciseCallback(
    private val provider: Provider<ExampleDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
    }
}