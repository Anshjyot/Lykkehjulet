package com.example.lykkehjulet.gamecomponents

import com.example.lykkehjulet.repo.animalList
import com.example.lykkehjulet.repo.fruitList
import com.example.lykkehjulet.repo.sportList

enum class Category {
    ANIMAL, SPORT, FRUIT
}

data class Words(
    val wordName: String
)

/**
 * return a word from the three different category
 */

fun getWordFromCategory(
    category: Category
): List<Words> {
    val wordsList = ArrayList<Words>()
    when (category) {
        Category.ANIMAL -> animalList()
        Category.SPORT -> sportList()
        Category.FRUIT -> fruitList()
    }.map {word -> wordsList.add(Words(wordName = word))}

    return wordsList
}