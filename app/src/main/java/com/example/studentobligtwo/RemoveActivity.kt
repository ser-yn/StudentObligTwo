package com.example.studentobligtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.studentobligtwo.Database.EntryDatabase
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.Database.EntryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RemoveActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var repository: EntryRepository
    private lateinit var entityListLive: LiveData<List<EntryEntity>>
    private lateinit var entityList: List<EntryEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove)

        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        entityListLive = repository.readAllData

        spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        entityListLive.observe(this, Observer {
            ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                it)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                }
            entityList = it
        })
    }


    fun removeEntry(view: View){
        Toast.makeText(this, entityList[spinner.selectedItemPosition].name + " has been removed from the Database", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            repository.deleteEntry(entityList[spinner.selectedItemPosition])
        }
    }
}