package com.example.guessthecat

data class Question(
    val id: Int,
    val question: String,
    val image: Int,
    val choiceOne: String,
    val choiceTwo: String,
    val choiceThree: String,
    val choiceFour: String,
    val correctAnswer: Int
)



