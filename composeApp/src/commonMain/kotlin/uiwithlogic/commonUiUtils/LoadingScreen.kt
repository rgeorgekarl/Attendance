package uiwithlogic.commonUiUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import attendance.composeapp.generated.resources.Res
import attendance.composeapp.generated.resources.frame_1
import attendance.composeapp.generated.resources.loading_img
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.getResourceImage

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = modifier.sizeIn(maxWidth = 300.dp, maxHeight = 300.dp).align(Alignment.Center),
            painter = getResourceImage("loading", "png"),
            contentDescription = "Loading",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}