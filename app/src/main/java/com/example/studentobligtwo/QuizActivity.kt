package com.example.studentobligtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentobligtwo.ViewModels.QuizViewModel

class QuizActivity : AppCompatActivity() {
    private lateinit var viewModel: QuizViewModel

    private lateinit var textViewRight: TextView
    private lateinit var textViewWrong: TextView

    private lateinit var imgView: ImageView

    private lateinit var butAnsOne: Button
    private lateinit var butAnsTwo: Button
    private lateinit var butAnsThree: Button
    private lateinit var butCheck: Button
    private lateinit var butPress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        textViewRight = findViewById(R.id.textView_rightAnswers)
        textViewWrong = findViewById(R.id.textView_wrongAnswers)

        imgView = findViewById(R.id.imageView)

        butAnsOne = findViewById(R.id.buttonAnswerOne)
        butAnsTwo = findViewById(R.id.buttonAnswerTwo)
        butAnsThree = findViewById(R.id.buttonAnswerThree)
        butCheck = findViewById(R.id.buttonCheck)

        textViewRight.text = "Right Answers: " + viewModel.rightCounter
        textViewWrong.text = "Wrong Answers: " + viewModel.wrongCounter
    }

    fun pressedAnswerButton(view: View) {
        viewModel.pressedButtonId = view.id

        butAnsOne.setBackgroundColor(resources.getColor(R.color.dark_Green))
        butAnsTwo.setBackgroundColor(resources.getColor(R.color.dark_Green))
        butAnsThree.setBackgroundColor(resources.getColor(R.color.dark_Green))

        butPress = findViewById(viewModel.pressedButtonId!!)
        butPress.setBackgroundColor(resources.getColor(R.color.light_Green))
        butCheck.isEnabled = true
    }

    fun evaluateAnswer(view: View) {
        viewModel.rightCounter++
        textViewRight.text = "Right Answers: " + viewModel.rightCounter
    }
}