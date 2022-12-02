package com.example.lykkehjulet.gamecomponents


class GameRepo {

    /**
     * Returns the list of category
     */
    fun getCategory(
        category: Category
    ): List<Words> {
        return getWordFromCategory(category)
    }

}