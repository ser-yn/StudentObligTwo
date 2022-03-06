package com.example.studentobligtwo.Database

import android.util.Log
import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDao) {

    val readAllData: LiveData<List<EntryEntity>> = entryDao.getAlphabetically()
    val readAllDataMutable: LiveData<MutableList<EntryEntity>> = entryDao.getMutable()

    suspend fun insert (entryEntity: EntryEntity){
        entryDao.insert(entryEntity)
    }

    suspend fun deleteAll(){
        entryDao.deleteAll()
    }
}