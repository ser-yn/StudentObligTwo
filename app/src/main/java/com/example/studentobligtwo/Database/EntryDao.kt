package com.example.studentobligtwo.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entryEntity: EntryEntity)

    @Delete
    fun delete(entryEntity: EntryEntity)

    @Query("DELETE FROM entry_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM entry_table ORDER BY name ASC")
    fun getAlphabetically(): LiveData<List<EntryEntity>>

    @Query("SELECT * FROM entry_table ORDER BY name ASC")
    fun getMutable(): LiveData<MutableList<EntryEntity>>
}