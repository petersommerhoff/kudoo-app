package com.petersommerhoff.kudoofinal

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.petersommerhoff.kudoofinal.db.dbScope
import com.petersommerhoff.kudoofinal.model.TodoItem
import com.petersommerhoff.kudoofinal.view.common.getViewModel
import com.petersommerhoff.kudoofinal.view.main.RecyclerListAdapter
import com.petersommerhoff.kudoofinal.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

  private lateinit var viewModel: TodoViewModel  // Now references view model, not DB

  val uiScope = CoroutineScope(coroutineContext + SupervisorJob())

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    viewModel = getViewModel(TodoViewModel::class)  // getViewModel is impl. next
    setUpRecyclerView()

    dbScope.launch {
      repeat(3) {
        delay(1000)
        viewModel.add(TodoItem("Celebrate!"))
      }
    }

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }
  }

  private fun setUpRecyclerView() {
    with(recyclerViewTodos) {
      adapter = RecyclerListAdapter(mutableListOf())
      layoutManager = LinearLayoutManager(this@MainActivity)
      itemAnimator = DefaultItemAnimator()
      addItemDecoration(
          DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }

    uiScope.launch {
      val todosLiveData = viewModel.getTodos()
      todosLiveData.observe(this@MainActivity, Observer { todos ->
        // Observes changes in the LiveData
        todos?.let {
          val adapter = (recyclerViewTodos.adapter as RecyclerListAdapter)
          adapter.setItems(it)  // Updates list items when data changes (next step)
        }
      })
    }
  }
}


