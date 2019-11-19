package com.kukicsevindik.motivationallist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* Created by Tatjana Kukic and Mert Sevindik on 13.11.2019 */

class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val taskTextView = itemView.findViewById<TextView>(R.id.textView_task) as TextView

}