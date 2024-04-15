package uiwithlogic.commonUiUtils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditButton(
    isEdit: Boolean,
    isEditable: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        imageVector = if (isEdit) Icons.Default.Edit else Icons.Filled.Cancel,
        contentDescription = "Edit",
        modifier = modifier
            .clickable { isEditable() }
            .size(48.dp)
            .clickable { isEditable() }
    )
}