package com.example.prioritizer.repository

import androidx.lifecycle.LiveData
import com.example.prioritizer.data.Todo
import com.example.prioritizer.data.TodoDao

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}