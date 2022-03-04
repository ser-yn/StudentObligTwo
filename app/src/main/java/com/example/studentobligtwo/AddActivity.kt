package com.example.studentobligtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.ViewModels.EntryViewModel

class AddActivity : AppCompatActivity() {

    private lateinit var mViewModel: EntryViewModel

    private lateinit var textOne: TextView
    private lateinit var textTwo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        mViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
    }

    fun insertDataInDatabase(view: View){
        textOne = findViewById(R.id.editOne)
        textTwo = findViewById(R.id.editTwo)

        val entry = EntryEntity (textOne.text.toString(), textTwo.text.toString())
        mViewModel.insert(entry)
    }
}