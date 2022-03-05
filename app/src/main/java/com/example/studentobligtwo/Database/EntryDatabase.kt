package com.example.studentobligtwo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//Our Database only has one table for the quiz, which is defined by our EntryEntity
@Database(entities = [EntryEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConvertUriString::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    //Since we only want one Instance of the Database, we need this in a singleton structure
    //When first created the instance of the Database is set to null
    //As soon as someone calls the getInstance, we check if the instance exists yet
    //if it exists, it gets returned, if it doesn't, it gets created and the instance variable isn't null anymore
    companion object{
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getDatabase(context: Context): EntryDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "entry_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
