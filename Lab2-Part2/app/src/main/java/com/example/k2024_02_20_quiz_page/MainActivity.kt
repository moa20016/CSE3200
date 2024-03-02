package com.example.k2024_02_20_quiz_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2024_02_20_quiz_page.controller.NextQuestion
import com.example.k2024_02_20_quiz_page.model.AllQuestions
import com.example.k2024_02_20_quiz_page.model.Question
import com.example.k2024_02_20_quiz_page.ui.theme.K2024_02_20_quiz_pageTheme
import com.example.k2024_02_20_quiz_page.ui.theme.Pink40

val allQuestions: AllQuestions = AllQuestions()

class MainActivity : ComponentActivity() {

    val nextQuestion: NextQuestion = NextQuestion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var myQuestion = allQuestions.getQuestion(2)

        setContent {
            K2024_02_20_quiz_pageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizPage("my Ultra Quiz!", nextQuestion)
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun QuizPage(name: String, nextQuestion: NextQuestion, modifier: Modifier = Modifier) {
    var questionNumber = remember { mutableStateOf(nextQuestion.getQuestionNumber())}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Card(modifier = Modifier.fillMaxWidth(0.75F)
            .fillMaxHeight(0.35F)) {
            Text(allQuestions.getQuestion(questionNumber.value).questionText)
        }
        Row()
        {
            Column {
               Button(onClick = { }) {
                   Text("True")
               }
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { questionNumber.value++ % allQuestions.getNumberOfQuestions()} ) {
                    Text("Next")
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Button(onClick = { }) {
                    Text("False")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { }) {
                    Text("Done")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2024_02_20_quiz_pageTheme {
        Greeting("Android")
    }
}