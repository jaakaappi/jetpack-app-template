package com.example.template.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.template.db.models.ExampleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleListViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {
    val examples = repository.getAllExamples()

    fun addExampleRow() = viewModelScope.launch {
        // Using 0 as the id of a new row makes Room generate the ID
        repository.addExample(ExampleModel(0))
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAllExamples()
    }
}