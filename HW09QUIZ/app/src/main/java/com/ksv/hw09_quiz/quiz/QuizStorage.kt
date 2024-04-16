package com.ksv.hw09_quiz.quiz

import java.util.Locale

object QuizStorage {
    private const val localeRU = "ru"

    fun getLocaleQuestion(number: Int): QuestionData? {
        return if (Locale.getDefault().language == localeRU) {
            when (number) {
                1 -> Question1Ru
                2 -> Question2Ru
                3 -> Question3Ru
                else -> null
            }
        } else {
            when (number) {
                1 -> Question1En
                2 -> Question2En
                3 -> Question3En
                else -> null
            }
        }
    }

    data class QuestionData(
        val text: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val right: Int
    )

    private val Question1En = QuestionData(
        text = "The longest river in the world?",
        answer1 = "Amazon",
        answer2 = "Neil",
        answer3 = "Mississippi",
        right = 1
    )
    private val Question1Ru = QuestionData(
        text = "Самая длинная река в мире?",
        answer1 = "Амазонка",
        answer2 = "Нил",
        answer3 = "Миссисипи",
        right = 1
    )

    private val Question2En = QuestionData(
        text = "The highest mountain in the world?",
        answer1 = "Makalu",
        answer2 = "Everest",
        answer3 = "Chogori",
        right = 2
    )
    private val Question2Ru = QuestionData(
        text = "Самая высокая гора в мире?",
        answer1 = "Макалу",
        answer2 = "Эверест",
        answer3 = "Чогори",
        right = 2
    )

    private val Question3En = QuestionData(
        text = "The deepest lake in the world?",
        answer1 = "Vostok lake",
        answer2 = "Tanganyika",
        answer3 = "Baikal",
        right = 3
    )
    private val Question3Ru = QuestionData(
        text = "Самое глубокое озеро в мире?",
        answer1 = "Озеро Восток",
        answer2 = "Танганьика",
        answer3 = "Байкал",
        right = 3
    )
}
