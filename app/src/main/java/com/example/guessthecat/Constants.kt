package com.example.guessthecat


//store all constants
object Constants {

    const val SHARED_PREFS_KEY = "CAT_APP"
    const val USERNAME: String = "USER_NAME"
    const val QUESTIONS: String = "ALL_QUESTIONS"
    const val CORRECT_ANSWERS : String = "CORRECT_RESPONSES"


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // question 1
        val q1 = Question(
                1,
                "Which breed of cat does this belong to?",
                R.drawable.ic_american_shorthair,
                "Bengal",
                "American Shorthair",
                "Bombay",
                "Birman",
                2)

        questionsList.add(q1)

        // question 2
        val q2 = Question(2,"Which breed of cat does this belong to?", R.drawable.ic_abyssinian,
                "Abyssinian",
                "Exotic Shorthair",
                "Burmese",
                "Ragdoll", 1)

        questionsList.add(q2)

        // question 3
        val q3 = Question(3,"Which breed of cat does this belong to?", R.drawable.ic_british_shorthair,
                "Maine Coon",
                "American Shorthair",
                "British Shorthair",
                "Birman", 3)

        questionsList.add(q3)


        // question 4
        val q4 = Question(4,"Which breed of cat does this belong to?", R.drawable.ic_norwegian_forest,
                "Russian Blue",
                "Siberian",
                "Munchkin",
                "Norwegian Forest", 4)

        questionsList.add(q4)


        // question 5
        val q5 = Question(5,"Which breed of cat does this belong to?", R.drawable.ic_birman,
            "Burmese",
            "Persian",
            "Birman",
            "Scottish Fold", 3)

        questionsList.add(q5)


        // question 6
        val q6 = Question(6,"Which breed of cat does this belong to?", R.drawable.ic_munchkin,
            "Munchkin",
            "British Fold",
            "Savannah Cat",
            "Korat", 1)

        questionsList.add(q6)


        // question 7
        val q7 = Question(7,"Which breed of cat does this belong to?", R.drawable.ic_siberian,
            "La Perm",
            "Snowshoe Cat",
            "Siberian",
            "Somali Cat", 3)

        questionsList.add(q7)


        // question 8
        val q8 = Question(8,"Which breed of cat does this belong to?", R.drawable.ic_sphynx,
            "Colorpoint Shorthair",
            "American Wirehair",
            "Turkish Van",
            "Sphynx", 4)

        questionsList.add(q8)


        // question 9
        val q9 = Question(9,"Which breed of cat does this belong to?", R.drawable.ic_bombay,
            "Bombay",
            "Chartreux",
            "Manx Cat",
            "Bengal", 1)

        questionsList.add(q9)

        // question 10
        val q10= Question(10,"Which breed of cat does this belong to?", R.drawable.ic_chartruex,
            "Korat",
            "Russian Blue",
            "Chartreux",
            "Tonkinese", 3)

        questionsList.add(q10)
        return questionsList
    }
}