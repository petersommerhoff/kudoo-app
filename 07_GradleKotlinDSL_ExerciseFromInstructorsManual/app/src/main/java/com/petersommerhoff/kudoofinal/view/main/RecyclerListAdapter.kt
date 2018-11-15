package com.petersommerhoff.kudoofinal.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petersommerhoff.kudoofinal.R
import com.petersommerhoff.kudoofinal.model.TodoItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.todo_item.*

/**
 * @author Peter Sommerhoff
 */
class RecyclerListAdapter(
    private val items: MutableList<TodoItem>,
    private val onItemCheckboxClicked: (TodoItem) -> Unit
) : RecyclerView.Adapter<RecyclerListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): ViewHolder {
    val itemView: View = LayoutInflater.from(parent.context)
        .inflate(R.layout.todo_item, parent, false)
    return ViewHolder(itemView)
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(items[position])
  }

  fun setItems(items: List<TodoItem>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
  }

  inner class ViewHolder(
      override val containerView: View
  ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(todoItem: TodoItem) {
      tvTodoTitle.text = todoItem.title
      cbTodoDone.isChecked = false

      cbTodoDone.setOnCheckedChangeListener { _, _ ->
        onItemCheckboxClicked(todoItem)
      }
    }
  }
}
