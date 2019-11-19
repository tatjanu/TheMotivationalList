package com.kukicsevindik.motivationallist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/* Created by Tatjana Kukic and Mert Sevindik on 08.11.2019 */

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<TaskList>, val clickListener: ListSelectionRecyclerViewClickListener) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    // Interface for when user taps a row
    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    // Create View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder, parent, false)
        return ListSelectionViewHolder(view)
    }

    // Get the amount of items from the list via size property
    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        // Check if there's a holder
        if (holder != null) {
            holder.listPosition.text = (position + 1).toString()
            holder.listTitle.text = lists.get(position).name
            holder.itemView.setOnClickListener({
                clickListener.listItemClicked(lists.get(position))
            })
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyDataSetChanged() // Uppdates the adapter
    }
}