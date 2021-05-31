package com.example.guessthecat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.guessthecat.Constants.SHARED_PREFS_KEY
import com.example.guessthecat.Constants.USERNAME

class QuestionsActivity : AppCompatActivity() {

    companion object {
        private val TAG = "DEBUG"
    }


    // declare the variables
    private var currPosition: Int = 1
    private var allQuestions: ArrayList<Question>? = null
    private var selectedPosition: Int = 0
    private var score: Int = 0
    private var userNameEditText: String? = null


    private lateinit var progressBar: ProgressBar
    private lateinit var submitButton: Button
    private lateinit var quizProgress: TextView
    private lateinit var quizQuestion: TextView
    private lateinit var imageView: ImageView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView




    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        //retrieve the data from previous activity
        userNameEditText = intent.getStringExtra(USERNAME)

        // hide the status bar
        window.setDecorFitsSystemWindows(false)

        val controller = window.insetsController
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // initialize the lateinit
        progressBar = findViewById(R.id.progressBarTextView)
        submitButton = findViewById(R.id.submitButton)
        quizProgress = findViewById(R.id.quizProgressNumberTextView)
        quizQuestion = findViewById(R.id.quizQuestionTextView)
        imageView = findViewById(R.id.imageView)
        optionOne = findViewById(R.id.choiceOneTextView)
        optionTwo = findViewById(R.id.choiceTwoTextView)
        optionThree = findViewById(R.id.choiceThreeTextView)
        optionFour = findViewById(R.id.choiceFourTextView)
        userNameEditText = findViewById(R.id.username)

        // get all the quiz questions
        allQuestions = Constants.getQuestions()

        //debug purposes
        Log.i("Question size", "${allQuestions!!.size}")

        // set all the quiz questions and relevant data
        setQuestion()

    }

    // hardware events
    override fun onPause(){
        super.onPause()
        //Log.d(TAG, "onPause() called");
        val prefsEditor = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE).edit()
        prefsEditor.putString(USERNAME, userNameEditText.toString())
        prefsEditor.apply()
    }


    override fun onResume(){
        super.onResume()
        //Log.d(TAG, "onResume() called");
        //Load data from shared prefs
        userNameEditText = findViewById(R.id.username)
        //retrieve the data from previous activity
        userNameEditText = intent.getStringExtra(USERNAME)


    }

    private fun setQuestion() {

        // assign all the relevant data
        // set all questions
        // currPosition = 1
        val question = allQuestions!![currPosition - 1]

        setDefaultQuestion()

        if(currPosition == allQuestions!!.size) {
            submitButton.text = "FINISH"
        }else{
            submitButton.text = "SUBMIT"
        }


        // set the progress bar
        progressBar.progress = currPosition
        quizProgress.text = "$currPosition" + "/" + progressBar.max

        // set the quiz question title
        quizQuestion.text = question.question

        // set the image
        imageView.setImageResource(question.image)

        // set all the options
        optionOne.text = question.choiceOne
        optionTwo.text = question.choiceTwo
        optionThree.text = question.choiceThree
        optionFour.text = question.choiceFour

    }

    private fun setDefaultQuestion() {
        val allMyChoices = ArrayList<TextView>()

        // add all the available question choices to the list of choices
        allMyChoices.add(0, optionOne)
        allMyChoices.add(1, optionTwo)
        allMyChoices.add(2, optionThree)
        allMyChoices.add(3, optionFour)


        //iterate through the options and assign the default border colour
        for (choice in allMyChoices) {

            //default option text colour
            choice.setTextColor(Color.parseColor("#7A8189"))

            //set selected choice border colour
            choice.typeface = Typeface.DEFAULT
            choice.background = ContextCompat.getDrawable(this, R.drawable.default_border)


        }

    }


    private fun selectedChoice(textView: TextView, selectedNumber: Int) {
        setDefaultQuestion()
        selectedPosition = selectedNumber

        //default option text colour
        textView.setTextColor(Color.parseColor("#4A4d55"))

        //set the border colour for the user selected option
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_choice_border)

    }

    private fun showAnswerColour(answer: Int, backgroundColour: Int){

        when(answer){
            1 -> {
                optionOne.background = ContextCompat.getDrawable(this, backgroundColour)
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(this, backgroundColour)
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(this, backgroundColour)
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(this, backgroundColour)
            }

        }

    }

    fun onSubmitClick(view: View) {
        when (view.id) {
            R.id.choiceOneTextView -> {
                selectedChoice(optionOne, 1)
            }
            R.id.choiceTwoTextView -> {
                selectedChoice(optionTwo, 2)
            }

            R.id.choiceThreeTextView -> {
                selectedChoice(optionThree, 3)
            }
            R.id.choiceFourTextView -> {
                selectedChoice(optionFour, 4)
            }
            R.id.submitButton -> {

                if (selectedPosition == 0) {
                    currPosition++

                    when {
                        currPosition <= allQuestions!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, EndOfQuizActivity::class.java)
                            intent.putExtra(USERNAME, userNameEditText)
                            intent.putExtra(Constants.CORRECT_ANSWERS, score)
                            intent.putExtra(Constants.QUESTIONS, allQuestions!!.size)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = allQuestions?.get(currPosition - 1)

                    //if incorrect option selected
                    if (question!!.correctAnswer != selectedPosition) {
                        showAnswerColour(selectedPosition, R.drawable.wrong_option_border)
                    } else {
                        score++
                    }

                    //correct option
                    showAnswerColour(question.correctAnswer, R.drawable.correct_option_border)

                    if (currPosition == allQuestions?.size) {
                        submitButton.text = "FINISH"
                    } else {
                        submitButton.text = "NEXT"
                    }

                    selectedPosition = 0

                }

            }
        }
    }




}