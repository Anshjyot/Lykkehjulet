package com.example.lykkehjulet.model
import androidx.compose.runtime.Stable


data class Word(
    @Stable val id: Int,
    val letter: String,
    var hasLetterBeenGuessed: Boolean = false
)
