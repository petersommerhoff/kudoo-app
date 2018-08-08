package com.petersommerhoff.kudoofinal.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.petersommerhoff.kudoofinal.db.*
import com.petersommerhoff.kudoofinal.model.TodoItem
import kotlinx.coroutines.experimental.*

/**
 * @author Peter Sommerhoff
 */
class TodoViewModel(app: Application) : AndroidViewModel(app) {
  private val dao by lazy { AppDatabase.getDatabase(getApplication()).todoItemDao() }

  fun getTodosAsync(): Deferred<MutableList<TodoItem>> = async(DB) {
    dao.loadAllTodos().toMutableList()
  }
  fun add(todo: TodoItem) = launch(DB) { dao.insertTodo(todo) }
  fun delete(todo: TodoItem) = launch(DB) { dao.deleteTodo(todo) }
}
