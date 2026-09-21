package com.example.unscramblee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val words: List<String> = listOf("CAT", "DOG", "BOOK")
    
    var currentWordIndex by mutableStateOf(0)
    var score by mutableStateOf(0)
    var userAnswer by mutableStateOf("")
}
