package com.example.guessthecat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class EndOfQuizActivity : AppCompatActivity() {

    //declare lateinit
    private lateinit var findUser: TextView
    private lateinit var revealScore: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_quiz)

        //find username
        val username = intent.getStringExtra(Constants.USERNAME)

        //initialize the lateinit
        findUser = findViewById(R.id.username)
        revealScore = findViewById(R.id.scoreId)

        findUser.text = username

        //find all the questions
        val totalQuestions = intent.getIntExtra(Constants.QUESTIONS, 0)

        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        revealScore.text = "Your score is $correctAnswers out of $totalQuestions"


    }

    fun onDoneButtonClicked(view: View){
        when(view.id){
            R.id.doneButton -> {
               startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }

}