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

    @Query("SELECT * FROM entry_table ORDER BY CASE WHEN :isAsc = 1 THEN name END ASC, CASE WHEN :isAsc = 0 THEN name END DESC")
    fun getAlphabetically(isAsc: Boolean): LiveData<List<EntryEntity>>
}