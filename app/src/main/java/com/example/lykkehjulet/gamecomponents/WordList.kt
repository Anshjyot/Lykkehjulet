package com.example.lykkehjulet.gamecomponents

import com.example.lykkehjulet.model.Word

/**
 * Code inspiration from https://stackoverflow.com/questions/63325684/how-to-create-a-mutable-list-of-alphabets-in-kotlin
 */

fun wordList(): MutableList<Word> {
    val letter = ('A'..'Z').toList()
    val letterId = (1..26).toList()
    val alphabets = letterId zip letter

    val wordList: MutableList<Word> = mutableListOf()

    alphabets.forEach { pair ->
        wordList.add(
            Word(
                id = pair.first,
                letter = pair.second.toString()
            )
        )
    }

    return wordList
}







