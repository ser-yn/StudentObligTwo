package com.example.studentobligtwo.ViewModels

import androidx.lifecycle.ViewModel

class EntryViewModel: ViewModel() {
    var number = 0
    fun addNumber(){
        number++
    }
}