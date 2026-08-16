package com.example.unscramblee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.unscramblee.ui.theme.UnscrambleeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UnscrambleeTheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun GameScreen() {

    var userAnswer by remember {
        mutableStateOf("")
    }

    val words = listOf(
        "CAT",
        "DOG",
        "BOOK"
    )

    var currentWordIndex by remember {
        mutableStateOf(0)
    }

    val correctAnswer = words[currentWordIndex]

    var score by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "UNSCRAMBLE",
            fontSize = 30.sp
        )

        Text(
            text = correctAnswer,
            fontSize = 40.sp
        )

        Text(
            text = "Unscramble the word!"
        )

        OutlinedTextField(
            value = userAnswer,
            onValueChange = {
                userAnswer = it
            },
            label = {
                Text("Enter your answer")
            }
        )

        Button(
            onClick = {
                if (userAnswer == correctAnswer) {
                    score++

                    if (currentWordIndex < words.size - 1) {
                        currentWordIndex++
                        userAnswer = ""
                    }
                }
            }
        ) {
            Text("SUBMIT")
        }

        Text(
            text = "Score: $score"
        )
    }
}