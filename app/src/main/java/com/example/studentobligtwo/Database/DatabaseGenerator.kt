package com.example.studentobligtwo.Database

import androidx.core.net.toUri

class DatabaseGenerator {
    companion object {

        fun getEntrys(): List<EntryEntity>{
            return listOf(
                EntryEntity("Wolf", "Noman".toUri()),
                EntryEntity("Bug", "Noman".toUri()),
                EntryEntity("Cow", "Noman".toUri()),
            )
        }
    }
}