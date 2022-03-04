package com.example.studentobligtwo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.ViewModels.EntryViewModel

class AddActivity : AppCompatActivity() {

    private lateinit var mViewModel: EntryViewModel

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var addButton: Button

    private lateinit var imgUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        textView = findViewById(R.id.add_name)
        imageView = findViewById(R.id.add_imageView)
        addButton = findViewById(R.id.add_AddButton)

        imageView.setImageDrawable(getDrawable(R.drawable.ic_placeholder))
        addButton.isEnabled = false

        mViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageView.setImageURI(uri)
        imgUri = uri!!
    }

    fun getImage(view: View){
        addButton.isEnabled = true
        getContent.launch("image/*")
    }

    fun addToDatabase(view: View){
        if(textView.text.trim().isEmpty()){
            Toast.makeText(this, "You need to enter a name", Toast.LENGTH_SHORT).show()
        }
        else{
            val entry = EntryEntity (textView.text.toString(), textView.text.toString())
            mViewModel.insert(entry)

            Toast.makeText(this, textView.text.toString() + " has been added to Database", Toast.LENGTH_SHORT).show()
            textView.text=""
            addButton.isEnabled = false
            imageView.setImageDrawable(getDrawable(R.drawable.ic_placeholder))
        }

    }
}