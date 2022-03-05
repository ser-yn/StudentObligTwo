package com.example.studentobligtwo.ViewModels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentobligtwo.Database.EntryDatabase
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.Database.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application): AndroidViewModel(application) {
    private val repository: EntryRepository

    var imgUri: Uri? = null

    init {
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
    }

    fun insert(entryEntity: EntryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(entryEntity)
        }
    }
}