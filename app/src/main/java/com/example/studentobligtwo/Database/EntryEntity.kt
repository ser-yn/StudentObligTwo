package com.example.studentobligtwo.Database

import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class which holds animal Pictures and Names
@Entity(tableName = "entry_table")
data class EntryEntity(
    @PrimaryKey
    val name: String,
    val imageResource: String)