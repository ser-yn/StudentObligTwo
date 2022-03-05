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
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModelProvider
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.ViewModels.EntryViewModel
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.studentobligtwo.ViewModels.AddViewModel


class AddActivity : AppCompatActivity() {

    private lateinit var mViewModel: AddViewModel

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        textView = findViewById(R.id.add_name)
        imageView = findViewById(R.id.add_imageView)
        addButton = findViewById(R.id.add_AddButton)

        if (mViewModel.imgUri!=null){
            imageView.setImageURI(mViewModel.imgUri)
            addButton.isEnabled = true
        }
        else {
            imageView.setImageDrawable(getDrawable(R.drawable.ic_placeholder))
            addButton.isEnabled = false
        }
    }

    /*
    * Creates an ActivityResultLauncher<Intent> that set URI for selected image on the imageView.
    * This is the "new" way of doing since onActivityResult was depricated.
    *
    * Also flags the view with URI permission so it can be opened later.
    *
    * I check if the user aborted the Imageselection
    * If he does i make the addButton unclickable again
    * If an Image was choosen before and the second selection was aborted the button stays clickable
    */
    private var chooseImageResult: ActivityResultLauncher<Intent>? = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode != RESULT_OK || result.data == null) {
            if (mViewModel.imgUri == null) {
                addButton.isEnabled = false
            }
            return@registerForActivityResult
        }
        mViewModel.imgUri = result.data!!.data
        contentResolver.takePersistableUriPermission(
            mViewModel.imgUri!!,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        imageView.setImageURI(mViewModel.imgUri)
    }

    // Makes the add Button clickable and starts the Intent to get a Image Uri from the Gallery
    fun getImage(view: View){
        addButton.isEnabled = true

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        chooseImageResult!!.launch(intent)
    }

    fun addToDatabase(view: View){
        if(textView.text.trim().isEmpty()){
            Toast.makeText(this, "You need to enter a name", Toast.LENGTH_SHORT).show()
        }
        else{
            val entry = EntryEntity (textView.text.toString(), mViewModel.imgUri!!)
            mViewModel.insert(entry)
            Toast.makeText(this, textView.text.toString() + " has been added to Database", Toast.LENGTH_SHORT).show()
            mViewModel.imgUri = null
            textView.text=""
            addButton.isEnabled = false
            imageView.setImageDrawable(getDrawable(R.drawable.ic_placeholder))
        }

    }
}