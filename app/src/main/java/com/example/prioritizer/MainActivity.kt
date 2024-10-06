package com.example.prioritizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prioritizer.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                TodoApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    var todoList by remember { mutableStateOf(listOf<String>()) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Prioritize Your Tasks !!") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFFAFAFA)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            if (textState.text.isEmpty()) {
                                Text(
                                    text = "Enter todo...",
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                Button(
                    onClick = {
                        if (textState.text.isNotEmpty()) {
                            todoList = todoList + textState.text
                            textState = TextFieldValue("")
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Add Todo")
                }
                todoList.forEach { todo ->
                    Text(
                        text = todo,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TodoAppTheme {
        TodoApp()
    }
}