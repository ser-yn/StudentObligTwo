package com.example.studentobligtwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.studentobligtwo.ViewModels.EntryViewModel

class DatabaseActivity : AppCompatActivity() {

    private lateinit var mEntryViewModel: EntryViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        adapter = ItemAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.Data_List_View)
        recyclerView.adapter = adapter

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        mEntryViewModel.readAllData.observe(this, Observer { entry ->
            adapter.setData(entry)
        })
    }

    fun startAdd(view: View) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    fun startRemove(view: View) {
        mEntryViewModel.deleteAll()
        val intent = Intent(this, RemoveActivity::class.java)
        startActivity(intent)
    }

    fun sort(view: View){
        mEntryViewModel.readAllData.observe(this, Observer { it ->
            it.sortWith(compareBy { it.name })
            if (!mEntryViewModel.alphabetBool){
                it.reverse()
            }
            mEntryViewModel.alphabetBool = !mEntryViewModel.alphabetBool
            adapter.notifyDataSetChanged()
        })
    }
}