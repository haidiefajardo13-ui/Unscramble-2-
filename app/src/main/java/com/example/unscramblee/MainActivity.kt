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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramblee.ui.theme.UnscrambleeTheme

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

    // Get the ViewModel
    val viewModel: GameViewModel = viewModel()

    // Scrambled word is still kept here temporarily
    var scrambledWord by remember {
        mutableStateOf(
            viewModel.words[viewModel.currentWordIndex]
                .toList()
                .shuffled()
                .joinToString("")
        )
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
            text = scrambledWord,
            fontSize = 40.sp
        )

        Text(
            text = "Unscramble the word!"
        )

        OutlinedTextField(
            value = viewModel.userAnswer,
            onValueChange = {
                viewModel.userAnswer = it
            },
            label = {
                Text("Enter your answer")
            }
        )

        Button(
            onClick = {

                if (viewModel.userAnswer == viewModel.words[viewModel.currentWordIndex]) {

                    viewModel.score++

                    if (viewModel.currentWordIndex < viewModel.words.size - 1) {

                        viewModel.currentWordIndex++

                        viewModel.userAnswer = ""

                        scrambledWord = viewModel.words[viewModel.currentWordIndex]
                            .toList()
                            .shuffled()
                            .joinToString("")
                    }
                }
            }
        ) {
            Text("SUBMIT")
        }

        Text(
            text = "Score: ${viewModel.score}"
        )
    }
}
