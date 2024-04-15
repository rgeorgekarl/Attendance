package uiwithlogic.inside.profile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import uiwithlogic.commonUiUtils.EditButton
import utils.ScreenType

@Composable
fun AccountDetailsScreen(
    firstName: String = "",
    onFirstNameChange: (String) -> Unit = {},
    middleName: String = "",
    onMiddleNameChange: (String) -> Unit = {},
    lastName: String = "",
    onLastNameChange: (String) -> Unit = {},
    nickName: String = "",
    onNickNameChange: (String) -> Unit = {},
    bio: String = "",
    onBioChange: (String) -> Unit = {},
    birth: String = "",
    onBirthChange: (String) -> Unit = {},
    age: String = "",
    school: String = "",
    onSchoolChange: (String) -> Unit = {},
    schoolYear: String = "",
    onSchoolYearChange: (String) -> Unit = {},
    course: String = "",
    onCourseChange: (String) -> Unit = {},
    contactNumber: String = "",
    onContactNumberChange: (String) -> Unit = {},
    address: String = "",
    onAddressChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    isEditable: () -> Unit = {},
    editable: Boolean = false,
    saveOnClick: () -> Unit = {},
    cancelOnClick: () -> Unit = {},
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    val modiDp = when (screenType) {
        ScreenType.COMPACT -> {
            modifier.padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 4.dp
            )
        }

        ScreenType.MEDIUM -> {
            modifier.padding(
                top = 16.dp,
                start = 25.dp,
                end = 25.dp,
                bottom = 4.dp
            )
        }

        ScreenType.EXPANDED -> {
            modifier.padding(
                top = 16.dp,
                start = 75.dp,
                end = 75.dp,
                bottom = 4.dp
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = modiDp,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            AccountT(
                screenType = screenType,
                firstName = firstName,
                onFirstNameChange = onFirstNameChange,
                middleName = middleName,
                onMiddleNameChange = onMiddleNameChange,
                lastName = lastName,
                onLastNameChange = onLastNameChange,
                nickName = nickName,
                onNickNameChange = onNickNameChange,
                bio = bio,
                onBioChange = onBioChange,
                birth = birth,
                onBirthChange = { onBirthChange(it) },
                age = age,
                school = school,
                onSchoolChange = onSchoolChange,
                schoolYear = schoolYear,
                onSchoolYearChange = onSchoolYearChange,
                course = course,
                onCourseChange = onCourseChange,
                contactNumber = contactNumber,
                onContactNumberChange = onContactNumberChange,
                address = address,
                onAddressChange = onAddressChange,
                email = email,
                onEmailChange = onEmailChange,
                isEditable = isEditable,
                editable = editable,
                saveOnClick = saveOnClick,
                cancelOnClick = cancelOnClick,
            )
        }
    }

}

@Composable
private fun AccountT(
    screenType: ScreenType,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    middleName: String,
    onMiddleNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    nickName: String,
    onNickNameChange: (String) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    birth: String,
    onBirthChange: (String) -> Unit,
    age: String,
    school: String,
    onSchoolChange: (String) -> Unit,
    schoolYear: String,
    onSchoolYearChange: (String) -> Unit,
    course: String,
    onCourseChange: (String) -> Unit,
    contactNumber: String,
    onContactNumberChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    isEditable: () -> Unit,
    editable: Boolean,
    saveOnClick: () -> Unit,
    cancelOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val verticalScrollState = rememberScrollState()
    val dpSize = when (screenType) {
        ScreenType.COMPACT -> {
            modifier.padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 4.dp
            )
        }

        ScreenType.MEDIUM -> {
            modifier.padding(
                top = 25.dp,
                start = 25.dp,
                end = 25.dp,
                bottom = 4.dp
            )
        }

        ScreenType.EXPANDED -> {
            modifier.padding(
                top = 16.dp,
                start = 75.dp,
                end = 75.dp,
                bottom = 4.dp
            )
        }
    }
    Box(modifier = Modifier) {
        EditButton(
            isEdit = editable,
            isEditable = isEditable,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = dpSize.padding(top = 20.dp)
                .verticalScroll(verticalScrollState)
        ) {
            AccountTitleText(text = "Account Details")
            AccountTextBox(
                value = firstName,
                onValueChange = { onFirstNameChange(it) },
                label = "First Name",
                readOnly = editable
            )
            AccountTextBox(
                value = middleName,
                onValueChange = onMiddleNameChange,
                label = "Middle Name",
                readOnly = editable
            )
            AccountTextBox(
                value = lastName,
                onValueChange = onLastNameChange,
                label = "Last Name",
                readOnly = editable
            )
            AccountHorizontalDivider()
            AccountTitleText(text = "Other Information")
            AccountTextBox(
                value = nickName,
                onValueChange = onNickNameChange,
                label = "Nick Name",
                readOnly = editable
            )
            AccountTextBox(
                value = bio,
                onValueChange = onBioChange,
                label = "Bio",
                readOnly = editable,
                modifier = Modifier.sizeIn(
                    maxHeight = 200.dp,
                    maxWidth = 1000.dp,
                    minWidth = 400.dp,
                    minHeight = 150.dp
                )
            )
            AccountHorizontalDivider()
            AccountTitleText(text = "ChildBirth")
            AccountDatePicker(
                birth = birth,
                getDate = {
                    onBirthChange(it.toString())
                }
            )
            AccountTextBox(
                value = age,
                onValueChange = { },
                label = "Age",
                readOnly = true
            )
            AccountHorizontalDivider()
            AccountTitleText(text = "Education")
            AccountTextBox(
                value = school,
                onValueChange = onSchoolChange,
                label = "School",
                readOnly = editable
            )
            AccountTextBox(
                value = schoolYear,
                onValueChange = { onSchoolYearChange(it) },
                label = "School Year",
                readOnly = editable
            )
            AccountTextBox(
                value = course,
                onValueChange = onCourseChange,
                label = "Course",
                readOnly = editable
            )
            AccountHorizontalDivider()
            AccountTitleText(text = "Contact")
            AccountTextBox(
                value = contactNumber,
                onValueChange = onContactNumberChange,
                label = "Contact Number",
                readOnly = editable
            )
            AccountTextBox(
                value = address,
                onValueChange = onAddressChange,
                label = "Address",
                readOnly = editable
            )
            AccountTextBox(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                readOnly = true,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            AnimatedVisibility(visible = !editable) {
                AccountHorizontalDivider()
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    AccountButton(
                        text = "Save",
                        onClick = saveOnClick,
                        modifier = Modifier.padding(8.dp)
                    )
                    AccountButton(
                        text = "Cancel",
                        onClick = cancelOnClick,
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountTitleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .padding(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountDatePicker(
    birth: String,
    getDate: (Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val current = birth.toLongOrNull() ?: Clock.System.now().toEpochMilliseconds()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = current)
        DatePicker(
            state = datePickerState,
            modifier = modifier.padding(16.dp).sizeIn(
                maxWidth = 600.dp,
                maxHeight = 600.dp
            )
        )
        getDate(datePickerState.selectedDateMillis ?: "0".toLong())
    }
}

@Composable
private fun AccountHorizontalDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        thickness = 2.dp,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
private fun AccountTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        label = { Text(label) },
        maxLines = maxLines,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    )
}

@Composable
fun AccountButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        modifier = modifier.size(width = 100.dp, height = 50.dp)
    ) {
        Text(text)
    }
}
