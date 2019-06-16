package com.petersommerhoff.kudoofinal.view.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.petersommerhoff.kudoofinal.R
import com.petersommerhoff.kudoofinal.db.dbScope
import com.petersommerhoff.kudoofinal.model.TodoItem
import com.petersommerhoff.kudoofinal.view.common.getViewModel
import com.petersommerhoff.kudoofinal.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.coroutines.launch

class AddTodoActivity : AppCompatActivity() {

  private lateinit var viewModel: TodoViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_todo)

    viewModel = getViewModel(TodoViewModel::class)
    setUpListeners()
  }

  private fun setUpListeners() {
    btnAddTodo.setOnClickListener {
      val newTodo = etNewTodo.text.toString()
      dbScope.launch { viewModel.add(TodoItem(newTodo)) }
      finish()
    }
  }
}
