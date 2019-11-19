package com.kukicsevindik.motivationallist

import android.content.Context
import android.preference.PreferenceManager

/* Created by Tatjana Kukic and Mert Sevindik on 09.11.2019 */

class ListDataManager(val context: Context) {

    // Save list
    fun saveList(list: TaskList) {
        // Edit()-method for reading and writing data in sharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        // Save my actual task list
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    // Read list
    fun readLists(): ArrayList<TaskList> {
        // No edit()-method because just reading
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Get contents of sharedPreferences
        val sharedPreferenceContents = sharedPreferences.all

        // Store Array TaskList, will return from this method
        val taskLists = ArrayList<TaskList>()

        // Loops through sharedPreferences contents, cast values into Set & add Set to taskList
        for (preferenceItem in sharedPreferenceContents) {
            val itemsHashSet = preferenceItem.value as HashSet<String>
            val list = TaskList(preferenceItem.key, ArrayList(itemsHashSet))
            taskLists.add(list)
        }
        return taskLists
    }
}