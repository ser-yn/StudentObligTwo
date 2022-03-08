package com.example.studentobligtwo.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entryEntity: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(entryEntityList: List<EntryEntity>)

    @Delete
    suspend fun delete(entryEntity: EntryEntity)

    @Query("SELECT * FROM entry_table ORDER BY name ASC")
    fun getAlphabetically(): LiveData<List<EntryEntity>>

    @Query("SELECT * FROM entry_table ORDER BY name ASC")
    fun getMutable(): LiveData<MutableList<EntryEntity>>
}