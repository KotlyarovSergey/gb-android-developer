package com.ksv.hw07_quiz.quiz

object QuizStorage {
    data class QuestionData(
        val text: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val right: Int
    )
    val Question1 = QuestionData(
        text = "Самая длинная река в мире?",
        answer1 = "Амазонка",
        answer2 = "Нил",
        answer3 = "Миссисипи",
        right = 1
    )
    val Question2 = QuestionData(
        text = "Самая высокая гора в мире?",
        answer1 = "Макалу",
        answer2 = "Эверест",
        answer3 = "Чогори",
        right = 2
    )
    val Question3 = QuestionData(
        text = "Самое глубокое озеро в мире?",
        answer1 = "Озеро Восток",
        answer2 = "Танганьика",
        answer3 = "Байкал",
        right = 3
    )
}
