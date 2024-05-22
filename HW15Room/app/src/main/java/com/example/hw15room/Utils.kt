package com.example.hw15room

class Utils {
    companion object {
        fun wordsToString(dictionaryItems: List<DictionaryItem>): String {
            val stringBuilder = StringBuilder()
            for (word in dictionaryItems) {
                if (word.count > 1)
                    stringBuilder.append("${word.word} (${word.count})\n")
                else
                    stringBuilder.append("${word.word}\n")
            }
            return stringBuilder.toString()
        }
    }
}