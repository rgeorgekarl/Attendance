package utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.*

@OptIn(InternalResourceApi::class, ExperimentalResourceApi::class)
@Composable
fun getResourceImage(imageName: String, imageType: String) = painterResource(
    DrawableResource(
        "drawable:$imageName",
        setOf(
            ResourceItem(
                setOf(),
                "drawable/$imageName.$imageType",
            )
        )
    )
)