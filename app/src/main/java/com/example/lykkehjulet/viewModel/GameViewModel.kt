package com.example.lykkehjulet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.lykkehjulet.gamecomponents.Category
import com.example.lykkehjulet.gamecomponents.Words
import com.example.lykkehjulet.gamecomponents.wordList
import com.example.lykkehjulet.model.Word
import com.example.lykkehjulet.gamecomponents.GameRepo
import kotlinx.coroutines.launch


class GameViewModel(
    private val repository: GameRepo
) : ViewModel() {


    private val updatePlayerGuess = mutableListOf<String>()
    private var _PlayerGuess = MutableLiveData(updatePlayerGuess)
    val PlayerGuess: LiveData<MutableList<String>>
        get() = _PlayerGuess

    private var _wordList = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>>
        get() = _wordList

    private var ifPlayerWon by mutableStateOf(false)
    var NoAttemptsLeft by mutableStateOf(false)
    var gameOverWon by mutableStateOf(false)
    var guessTheWord: String by mutableStateOf("")
    var attemptsLeft: Int by mutableStateOf(5)

    private var _wordToGuess = MutableLiveData(NoAttemptsLeft)
    val wordToGuess: LiveData<Boolean>
        get() = _wordToGuess


    var points: Int by mutableStateOf(0)
    var pointsSummary: Int by mutableStateOf(0)

    var category = mutableStateOf(Category.values().toList().shuffled().random())
    private var randomwordlist: List<Words> by mutableStateOf(listOf())
    var click by mutableStateOf(false)
    private var start by mutableStateOf(true)



    init {
        viewModelScope.launch {

            randomwordlist = repository.getCategory(category.value)
            guessTheWord = randomwordlist.shuffled().random().wordName

            resetWordToGuess()

            points = calculatePoint(spinValue())
            pointsSummary = calculatePoint(spinValue())

        }

    }

    fun checkIfLetterMatches(
        alphabet: Word
    ) {
        viewModelScope.launch {
            val currentAlphabet: String = alphabet.letter.lowercase()
            val currentGuessingWord: String = guessTheWord.lowercase()
            click = false
            if (currentGuessingWord.contains(currentAlphabet)) {
                // letter match.
                for (notI in currentGuessingWord.indices) {
                    // position the letter is
                    if (currentGuessingWord[notI].toString() == currentAlphabet) {
                        // show letter
                        updatePlayerGuess[notI] = currentAlphabet
                    }
                }

                // word correct
                if (!updatePlayerGuess.contains(" ")) {
                    ifPlayerWon = true
                    NoAttemptsLeft = false
                    _wordToGuess.value = NoAttemptsLeft
                }
            } else {
                minimizeAttempt()
            }

                if (ifPlayerWon) {
                    gameOverWon = true

                }

            }

        }



    private fun calculatePoint(value: String): Int {
        if(start) {
            start = false
            pointsSummary = 0
            points = 0
            return pointsSummary
            return points
        }
        if (value == "500") {
            points += 500
        } else if (value == "700")
            points  += 700
        else if (value == "900")
            points  += 900
        else if (value == "300")
            points  += 300
        else if (value == "800")
            points  += 800
        else if (value == "550")
            points  += 550
        else if (value == "600")
            points += 600
        else if (value== "Bankrupt")
            pointsSummary = 0
        else if (value == "400")
            points  += 400

        pointsSummary += points
        return pointsSummary
    }


    private fun minimizeAttempt() {
        if (attemptsLeft > 0) {
            attemptsLeft -= 1
            NoAttemptsLeft = attemptsLeft == 0
            _wordToGuess.value = NoAttemptsLeft

        }
    }


    private fun resetWordToGuess() {

        _wordList.value = wordList()
        updatePlayerGuess.clear()
        for (i in guessTheWord.indices) {
            updatePlayerGuess.add(" ")
        }

    }




    fun spinValue(): String {
        val spinlist = listOf(
            "500",
            "700",
            "900",
            "300",
            "800",
            "550",
            "600",
            "Bankrupt",
            "500",
            "300",
            "400",
            "Bankrupt",
        )

        return spinlist.random()


    }


}

