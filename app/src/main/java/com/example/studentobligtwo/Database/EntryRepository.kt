package com.example.studentobligtwo.Database

import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDao) {

    val readAllData: LiveData<List<EntryEntity>> = entryDao.getAlphabetizedEntrys()

    suspend fun insert (entryEntity: EntryEntity){
        entryDao.insert(entryEntity)
    }
}