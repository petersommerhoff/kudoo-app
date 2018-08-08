package com.petersommerhoff.kudoofinal.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.petersommerhoff.kudoofinal.db.AppDatabase
import com.petersommerhoff.kudoofinal.db.DB
import com.petersommerhoff.kudoofinal.model.TodoItem
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Peter Sommerhoff
 */
class TodoViewModel(app: Application) : AndroidViewModel(app) {
  private val dao by lazy { AppDatabase.getDatabase(getApplication()).todoItemDao() }

  // Now uses a LiveData of a read-only list
  fun getTodosAsync(): Deferred<LiveData<List<TodoItem>>> = async(DB) {
    dao.loadAllTodos()
  }
  fun add(todo: TodoItem) = launch(DB) { dao.insertTodo(todo) }
  fun delete(todo: TodoItem) = launch(DB) { dao.deleteTodo(todo) }
}
