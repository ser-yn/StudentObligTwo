package com.example.studentobligtwo.Database

import android.net.Uri
import androidx.room.TypeConverter

class ConvertUriString {

    @TypeConverter
    fun fromUri(uri: Uri): String {
        return uri.toString()
    }
    @TypeConverter
    fun toUri(string: String): Uri{
        return Uri.parse(string)
    }
}