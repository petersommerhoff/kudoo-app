package com.petersommerhoff.kudoofinal.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
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
