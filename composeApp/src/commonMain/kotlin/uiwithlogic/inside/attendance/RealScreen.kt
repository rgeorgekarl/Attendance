package uiwithlogic.inside.attendance

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RealScreen(
    userOnClick: () -> Unit,
    manageOnClick: () -> Unit
){
    Column(modifier = Modifier.padding(top = 60.dp).fillMaxSize()
    ){
        Card(
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Choose",
                    style = MaterialTheme.typography.displaySmall
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Button(
                        onClick = { userOnClick() },
                        modifier = Modifier.size(120.dp, 50.dp)
                    ) {
                        Text(
                            "User",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { manageOnClick() },
                        modifier = Modifier.size(120.dp, 50.dp)

                    ) {
                        Text(
                            "Manage",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }

    }
}