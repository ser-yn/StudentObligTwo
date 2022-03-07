package com.example.studentobligtwo.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studentobligtwo.Database.EntryDatabase
import com.example.studentobligtwo.Database.EntryEntity
import com.example.studentobligtwo.Database.EntryRepository

class QuizViewModel(application: Application): AndroidViewModel(application) {

    var wasRotated: Boolean = false

    var rightCounter: Int = 0
    var wrongCounter: Int = 0

    var rightButtonId: Int = 0
    var wrongButtonId: Int = 0
    var pressedButtonId: Int? = 0

    var rightAnswer: Int = 0
    var wrongAnswerOne: Int = 0
    var wrongAnswerTwo: Int = 0
    var randButton: Int = 0

    var entityList: LiveData<List<EntryEntity>>
    private val repository: EntryRepository

    init {
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        entityList = repository.readAllData
    }
}