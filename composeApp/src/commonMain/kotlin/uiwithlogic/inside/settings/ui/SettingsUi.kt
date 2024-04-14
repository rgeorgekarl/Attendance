package uiwithlogic.inside.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalGridList(
    colors: List<Color>,
    selected: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        items(colors.size) { index ->
            ThemeItemCard(
                color = colors[index],
                onClick = { selected(index + 1) }
            )
        }
    }
}

@Composable
fun ThemeItemCard(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp),
            )
            .clickable { onClick() }
    )
}

@Composable
fun LightOrDark(
    chosen: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.LightMode,
                contentDescription = "Light Mode",
                tint = Color.White,
                modifier = Modifier.clickable { chosen(false) }
            )
            Icon(
                imageVector = Icons.Default.DarkMode,
                contentDescription = "Dark Mode",
                tint = Color.Black,
                modifier = Modifier.clickable { chosen(true) }
            )
        }

    }
}
