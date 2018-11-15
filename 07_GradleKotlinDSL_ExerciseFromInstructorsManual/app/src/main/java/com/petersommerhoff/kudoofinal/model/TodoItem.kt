package com.petersommerhoff.kudoofinal.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Peter Sommerhoff
 */
@Entity(tableName = "todos")
data class TodoItem(val title: String) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0  // 0 is considered not set by Room
}
