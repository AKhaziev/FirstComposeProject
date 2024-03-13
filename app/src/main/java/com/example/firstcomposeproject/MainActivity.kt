package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimesTable()
//            Greeting()
//            UserInfo(name = "Александр", age = 43)
        }
    }
}

@Preview
@Composable
fun TimesTable() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in 1 until 10) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                for (j in 1 until 10) {
                    val color = if ((j + i) % 2 == 0) Color.Yellow else Color.White

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .border(width = 2.dp, color = Color.DarkGray)
                            .background(color),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "${i * j}", color = Color.Black
                        )
                    }

                }
            }
        }
    }

}

//@Preview
@Composable
fun Greeting() {
    val name = "Android"
    Text(
        text = "Hello $name!!!", color = Color.Green
    )
}

//@Preview
@Composable
fun UserInfoPreview() {
    UserInfo(name = "Alexander", age = 43)
}


@Composable
fun UserInfo(name: String, age: Int) {

    Column {
        repeat(times = 10) {
            Text(
                text = "Hello $name, you are $age years old", color = Color.White
            )
        }


    }
}
