package com.petersommerhoff.kudoofinal.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import android.widget.Toast
import com.petersommerhoff.kudoofinal.db.AppDatabase
import com.petersommerhoff.kudoofinal.db.DB
import com.petersommerhoff.kudoofinal.model.TodoItem
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

/**
 * @author Peter Sommerhoff
 */
class TodoViewModel(app: Application) : AndroidViewModel(app) {
  private val dao by lazy { AppDatabase.getDatabase(getApplication()).todoItemDao() }

  suspend fun getTodos(): LiveData<List<TodoItem>> = withContext(DB) {
    dao.loadAllTodos()
  }

  fun add(todo: TodoItem) = launch(DB) { dao.insertTodo(todo) }

  fun delete(todo: TodoItem) = launch(DB) { dao.deleteTodo(todo) }
}
