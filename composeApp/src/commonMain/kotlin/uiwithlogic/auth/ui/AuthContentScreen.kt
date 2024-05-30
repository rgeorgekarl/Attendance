package uiwithlogic.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uiwithlogic.auth.model.AuthType
import utils.ScreenType
import utils.getResourceImage

@Composable
internal fun AuthContentScreen(
    screenType: ScreenType,
    auth: AuthType,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    authButton: () -> Unit,
    onAuthTypeChange: () -> Unit,
        modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            if (screenType == ScreenType.EXPANDED) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    AuthLogo(screenType = screenType)
                    Column {
                        AuthTypeRow(
                            auth = auth,
                            onAuthTypeChange = onAuthTypeChange,
                            firstTextStyle = MaterialTheme.typography.displayMedium,
                            secondTextStyle = MaterialTheme.typography.labelLarge
                        )
                        AuthTextBox(
                            value = email,
                            onValueChange = onEmailChange,
                            label = "Email",
                            isError = false
                        )
                        AuthTextBox(
                            value = password,
                            onValueChange = onPasswordChange,
                            label = "Password",
                            isError = false
                        )
                        AuthButton(
                            text = auth.name,
                            onClick = authButton,
                            screenType = screenType
                        )
                    }
                }
            } else {
                AuthLogo(screenType = screenType)
                AuthTypeRow(
                    auth = auth,
                    onAuthTypeChange = onAuthTypeChange,
                    firstTextStyle = MaterialTheme.typography.displaySmall,
                    secondTextStyle = MaterialTheme.typography.labelMedium
                )

                AuthTextBox(
                    value = email,
                    onValueChange = onEmailChange,
                    label = "Email",
                    isError = false
                )
                AuthTextBox(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = "Password",
                    isError = false
                )
                AuthButton(
                    text = auth.name,
                    onClick = authButton,
                    screenType = screenType
                )
            }
        }
    }
}


@Composable
private fun AuthTypeRow(
    auth: AuthType,
    onAuthTypeChange: () -> Unit,
    firstTextStyle: TextStyle = MaterialTheme.typography.displaySmall,
    secondTextStyle: TextStyle = MaterialTheme.typography.labelMedium
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
    ) {
        Text(
            text = auth.name,
            style = firstTextStyle,
        )
        Text(
            text = if (auth.name == "LOGIN") AuthType.SIGNUP.name else AuthType.LOGIN.name,
            style = secondTextStyle,
            modifier = Modifier.clickable {
                onAuthTypeChange()
            }
        )
    }
}

@Composable
private fun AuthButton(
    text: String,
    onClick: () -> Unit,
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    val size = when (screenType) {
        ScreenType.COMPACT -> 150
        else -> 200
    }
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(size.dp)
        )
    }
}

@Composable
private fun AuthTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        visualTransformation = if (label == "Password") PasswordVisualTransformation() else VisualTransformation.None,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = if (label == "Password") KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
    )
}

@Composable
private fun AuthLogo(
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    val dpSize = when (screenType) {
        ScreenType.COMPACT -> 200
        ScreenType.MEDIUM -> 200
        ScreenType.EXPANDED -> 300
    }
    Box {
        Image(
            painter = getResourceImage(imageName = "smc", "png"),
            contentDescription = "Logo",
            modifier = modifier.size(dpSize.dp).padding(0.dp)
        )
    }
}