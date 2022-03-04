package com.example.studentobligtwo.Database

import android.util.Log
import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDao) {

    val readAllData: LiveData<List<EntryEntity>> = entryDao.getAlphabetically(true)

    suspend fun insert (entryEntity: EntryEntity){
        entryDao.insert(entryEntity)
    }

    fun sortAlphabetically (reverseBool: Boolean){
        entryDao.getAlphabetically(reverseBool)
        Log.w("myApp", "AlphaWorks");
    }
}