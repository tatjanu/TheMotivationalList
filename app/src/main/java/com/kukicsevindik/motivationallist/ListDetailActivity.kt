package com.kukicsevindik.motivationallist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/* Created by Tatjana Kukic and Mert Sevindik on 13.11.2019 */

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView
    lateinit var addTaskButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name
        // RecyclerView setup for list items
        listItemsRecyclerView = findViewById<RecyclerView>(R.id.list_items_recyclerview)
        // Set up its layoutManager
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        // Create an instance of RecyclerViewAdapter
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
        // Find floating action button
        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        // Add a click listener
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this).setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task, { dialog, _ ->
                val task = taskEditText.text.toString()
                list.tasks.add(task)
                val recyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
                recyclerAdapter.notifyItemInserted(list.tasks.size)
                dialog.dismiss()
            }).create().show()
    }

    // Save list when pressing back button
    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)
        val  intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}
