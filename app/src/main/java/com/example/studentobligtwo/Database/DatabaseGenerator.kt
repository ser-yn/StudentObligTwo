package com.example.studentobligtwo.Database

import androidx.core.net.toUri
import com.example.studentobligtwo.R

class DatabaseGenerator {
    companion object {

        fun getEntrys(): List<EntryEntity>{
            return listOf(
                EntryEntity("Wolf", ("android.resource://com.example.studentobligtwo/"+R.drawable.wolf).toUri()),
                EntryEntity("Lion", ("android.resource://com.example.studentobligtwo/"+R.drawable.lion).toUri()),
                EntryEntity("Whale", ("android.resource://com.example.studentobligtwo/"+R.drawable.whale).toUri()),
            )
        }
    }
}