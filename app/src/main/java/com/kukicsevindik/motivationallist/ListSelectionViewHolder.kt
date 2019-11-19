package com.kukicsevindik.motivationallist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* Created by Tatjana Kukic and Mert Sevindik on 08.11.2019 */

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val listPosition = itemView.findViewById<TextView>(R.id.itemNumber) as TextView
    val listTitle = itemView.findViewById<TextView>(R.id.itemString) as TextView
}