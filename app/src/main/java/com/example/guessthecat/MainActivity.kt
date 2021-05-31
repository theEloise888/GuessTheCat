package com.example.guessthecat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // declare the lateint
    private lateinit var editTextName: EditText


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // hide the status bar
        window.setDecorFitsSystemWindows(false)

        val controller = window.insetsController
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // initialize the lateinit
        editTextName = findViewById(R.id.nameEditText)

    }


    fun onStartClick(v: View?) {
            when(v?.id){
                R.id.startButton->{
                    if(editTextName.text.toString().isEmpty()){
                        //inform user
                        Toast.makeText(applicationContext, "ERROR: Name is missing. Please enter your name.", Toast.LENGTH_SHORT).show()
                    } else{
                        // move to second activity
                        val intent  = Intent(this, QuestionsActivity::class.java)
                        //save the data and send to next activity
                        intent.putExtra(Constants.USERNAME, editTextName.text.toString())
                        startActivity(intent)
                        finish()
                    }
                }

            }
        }



}