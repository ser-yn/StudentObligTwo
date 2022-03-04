package com.example.studentobligtwo.ViewModels

import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {
    var rightCounter: Int = 0
    var wrongCounter: Int = 0

    var rightButtonId: Int = 0
    var pressedButtonId: Int? = 0

    fun incrementRight(){
        rightCounter++
    }
    fun incrementWrong(){
        wrongCounter++
    }
}