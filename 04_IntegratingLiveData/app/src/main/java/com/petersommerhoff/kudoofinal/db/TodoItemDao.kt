package com.petersommerhoff.kudoofinal.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import com.petersommerhoff.kudoofinal.model.TodoItem

/**
 * @author Peter Sommerhoff
 */
@Dao
interface TodoItemDao {

  @Query("SELECT * FROM todos")
  fun loadAllTodos(): LiveData<List<TodoItem>>  // Wraps return type in LiveData now

  @Insert(onConflict = IGNORE)
  fun insertTodo(todo: TodoItem)

  @Delete
  fun deleteTodo(todo: TodoItem)
}
