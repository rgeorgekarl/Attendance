package uiwithlogic.inside.home.ui

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Card {
        Text(
            text = "Welcome to my life",
            style = MaterialTheme.typography.displaySmall
        )
    }
}


