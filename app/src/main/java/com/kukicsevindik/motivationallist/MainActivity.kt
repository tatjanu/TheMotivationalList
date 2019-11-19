package com.kukicsevindik.motivationallist

/* Created by Tatjana Kukic and Mert Sevindik on 08.11.2019 */

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    lateinit var listsRecyclerView: RecyclerView
    val listDataManager: ListDataManager = ListDataManager(this)

    // Listkey for the intent
    companion object {
        val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showCreateListDialog()
        }

        // Get our data (for RecyclerView)
        val lists = listDataManager.readLists()

        // Add a RecyclerView
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        // Provide a Layoutmanager
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        // Add an adapter for RecyclerView
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialog() {
        // Reference to Strings for Dialog
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        // Creating an instance of the dialog by calling a builder constructor
        val builder = AlertDialog.Builder(this)

        // Create EditText UI Element
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        // Set the dialog title & edit text
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        // Inform dialog builder
        builder.setPositiveButton(positiveButtonTitle, { dialog, i ->

            // Get title from user & save it
            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            // Update the RecyclerView
            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addList(list)

            dialog.dismiss()
            // Can immediatly start adding items to list
            showListDetail(list)
        })

        // Show the dialog
        builder.create().show()
    }

    private fun showListDetail(list: TaskList) {
        // Create an intent
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        startActivity(listDetailIntent)
    }

    override fun listItemClicked(list: TaskList) {
        showListDetail(list)
    }
}
