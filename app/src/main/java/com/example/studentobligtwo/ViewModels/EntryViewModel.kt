package com.example.studentobligtwo.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.studentobligtwo.Database.EntryDatabase
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.Database.EntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<EntryEntity>>
    private val repository: EntryRepository
    private var alphabetBool: Boolean

    init {
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        readAllData = repository.readAllData
        alphabetBool = false
    }

    fun insert(entryEntity: EntryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(entryEntity)
        }
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun sortAlphabetically(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.sortAlphabetically(alphabetBool)
        }
        alphabetBool =! alphabetBool

        Log.w("myApp", alphabetBool.toString());
    }
}