package uiwithlogic.inside.attendance.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AttendanceScreen(){
    Column(modifier = Modifier.padding(top = 56.dp)
    ){
        Text(
            text = "Attendance Screen",
            style = MaterialTheme.typography.titleLarge
        )
    }
}