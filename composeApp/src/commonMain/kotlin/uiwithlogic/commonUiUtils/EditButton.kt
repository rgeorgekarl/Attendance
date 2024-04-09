package uiwithlogic.commonUiUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditButton(
    isEdit: Boolean,
    isEditable: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = if (isEdit) Icons.Default.Edit else Icons.Filled.EditOff,
        contentDescription = "Edit",
        modifier = modifier.size(24.dp)
            .clickable { isEditable()}
    )
}