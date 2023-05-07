package com.example.template

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.template.db.ExampleListViewModel
import com.example.template.ui.theme.TemplateTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Card(
                        backgroundColor = MaterialTheme.colors.surface,
                        modifier = Modifier.padding(all = 12.dp)
                    ) {
                        ExampleList()
                    }
                }
            }
        }
    }
}

@Composable
fun ExampleList(exampleListViewModel: ExampleListViewModel = viewModel()) {
    val exampleRows by exampleListViewModel.examples.collectAsState(initial = listOf())

    Column(
        modifier = Modifier.padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ExampleButton {
                        Text(text = "Add new",
                            fontSize = 20.sp,
                            modifier = Modifier.clickable { exampleListViewModel.addExampleRow() })
                    }
                    ExampleButton {
                        Text(text = "Delete all",
                            fontSize = 20.sp,
                            modifier = Modifier.clickable { exampleListViewModel.deleteAll() })
                    }
                }
            }
        }
        Text(text = "Rows in database", fontSize = 20.sp)
        Divider()
        if (exampleRows.isNotEmpty())
            exampleRows.map {
                Text(text = "ID: ${it.id.toString()}")
            }
        else {
            Text(text = "No rows")
        }
    }
}

@Composable
fun ExampleButton(content: @Composable() () -> Unit) {
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(20))
            .padding(all = 12.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        content()
    }
}