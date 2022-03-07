package com.example.studentobligtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentobligtwo.ViewModels.QuizViewModel
import android.content.pm.ActivityInfo
import android.util.Log


class QuizActivity : AppCompatActivity() {
    //Public for Testing
    lateinit var viewModel: QuizViewModel

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

        if (viewModel.wasRotated) {
            Log.w("OrientChange", "Was Rotated")
            setKnownAnswerAndPicture()
        } else {
            Log.w("OrientChange", "Was Not Rotated")
            setPictureAndAnswersRandom()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.wasRotated = true
    }

    private fun setKnownAnswerAndPicture(){
        viewModel.entityList.observe(this, Observer {

            imgView.setImageURI(it[viewModel.rightAnswer].imageResource)

            when(viewModel.randButton){
                0 -> {
                    butAnsOne.text = it[viewModel.rightAnswer].name
                    butAnsTwo.text = it[viewModel.wrongAnswerOne].name
                    butAnsThree.text = it[viewModel.wrongAnswerTwo].name

                    viewModel.rightButtonId = R.id.buttonAnswerOne
                }
                1 -> {
                    butAnsOne.text = it[viewModel.wrongAnswerOne].name
                    butAnsTwo.text = it[viewModel.rightAnswer].name
                    butAnsThree.text = it[viewModel.wrongAnswerTwo].name

                    viewModel.rightButtonId = R.id.buttonAnswerTwo
                }
                2 -> {
                    butAnsOne.text = it[viewModel.wrongAnswerOne].name
                    butAnsTwo.text = it[viewModel.wrongAnswerTwo].name
                    butAnsThree.text = it[viewModel.rightAnswer].name

                    viewModel.rightButtonId = R.id.buttonAnswerThree
                }
            }
        })
    }

    private fun setPictureAndAnswersRandom(){
        viewModel.entityList.observe(this, Observer {

            val listSize: Int = it.size
            var rightAnswer: Int = (0 until listSize).random()
            var wrongAnswerOne: Int = (0 until listSize).random()
            var wrongAnswerTwo: Int = (0 until listSize).random()
            //Not to get the same Value Twice I firstly check the first wrong then the second wrong answer
            //The while loop gets broken once it's not the same value anymore
            while (true) {
                if (rightAnswer==wrongAnswerOne){
                    wrongAnswerOne = (0 until listSize).random()
                }
                else{
                    break
                }
            }
            while (true) {
                if (rightAnswer==wrongAnswerTwo || wrongAnswerOne==wrongAnswerTwo){
                    wrongAnswerTwo = (0 until listSize).random()
                }
                else{
                    break
                }
            }

            //Add to view Model so same Picture is set on rotation Change
            viewModel.rightAnswer = rightAnswer
            viewModel.wrongAnswerOne = wrongAnswerOne
            viewModel.wrongAnswerTwo = wrongAnswerTwo

            imgView.setImageURI(it[rightAnswer].imageResource)

            viewModel.randButton = (0 until 3).random()
            when(viewModel.randButton){
                0 -> {
                    butAnsOne.text = it[rightAnswer].name
                    butAnsTwo.text = it[wrongAnswerOne].name
                    butAnsThree.text = it[wrongAnswerTwo].name

                    viewModel.wrongButtonId = R.id.buttonAnswerTwo
                    viewModel.rightButtonId = R.id.buttonAnswerOne
                }
                1 -> {
                    butAnsOne.text = it[wrongAnswerOne].name
                    butAnsTwo.text = it[rightAnswer].name
                    butAnsThree.text = it[wrongAnswerTwo].name

                    viewModel.wrongButtonId = R.id.buttonAnswerOne
                    viewModel.rightButtonId = R.id.buttonAnswerTwo
                }
                2 -> {
                    butAnsOne.text = it[wrongAnswerOne].name
                    butAnsTwo.text = it[wrongAnswerTwo].name
                    butAnsThree.text = it[rightAnswer].name

                    viewModel.wrongButtonId = R.id.buttonAnswerTwo
                    viewModel.rightButtonId = R.id.buttonAnswerThree
                }
            }
        })
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
        butCheck.isEnabled = false
        butPress.setBackgroundColor(resources.getColor(R.color.dark_Green))
        if(viewModel.pressedButtonId==viewModel.rightButtonId){
            viewModel.rightCounter++
            textViewRight.text = "Right answers: " + viewModel.rightCounter.toString()
            Toast.makeText(this, "Great, you're right", Toast.LENGTH_SHORT).show()
        }
        else {
            var butTempRight: Button = findViewById(viewModel.rightButtonId)
            viewModel.wrongCounter++
            textViewWrong.text = "Wrong answers: " + viewModel.wrongCounter.toString()
            Toast.makeText(this, "Correct Answer is: " + butTempRight.text, Toast.LENGTH_SHORT).show()
        }

        setPictureAndAnswersRandom()
    }
}