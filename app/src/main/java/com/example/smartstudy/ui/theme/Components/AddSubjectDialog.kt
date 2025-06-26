import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.smartstudy.ui.theme.domain.model.Subject

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add/Update",
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    selectedColor: List<Color>,
    onColorChange: (List<Color>) -> Unit,
    subjectName: String,
    goalHour: String,
    onSubjectNameChange: (String) -> Unit,
    onGoalHourChange: (String) -> Unit
) {
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var goalHourError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError = when {
        subjectName.isBlank() -> "Please enter the subject name"
        subjectName.length < 2 -> "Subject name is too short"
        subjectName.length > 20 -> "Subject name is too large"
        else -> null
    }

    goalHourError = when {
        goalHour.isBlank() -> "Please enter the goal hour"
        goalHour.toFloatOrNull() == null -> "Invalid number"
        goalHour.toFloat() < 1f -> "Minimum hour is 1"
        goalHour.toFloat() > 1000f -> "Maximum hour is 1000"
        else -> null
    }

    if (isOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Subject.subjectCardColors.forEach { colors ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .border(
                                        width = 1.dp,
                                        color = if (colors == selectedColor) Color.Black
                                        else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .background(brush = Brush.verticalGradient(colors))
                                    .clickable { onColorChange(colors) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = subjectName,
                        onValueChange = onSubjectNameChange,
                        label = { Text(text = "Subject Name") },
                        singleLine = true,
                        isError = !subjectNameError.isNullOrEmpty() ,
                        supportingText = { Text(text = subjectNameError.orEmpty()) }
                    )
                    OutlinedTextField(
                        value = goalHour,
                        onValueChange = onGoalHourChange,
                        label = { Text(text = "Goal Study Hour") },
                        singleLine = true,
                        isError = !goalHourError.isNullOrEmpty() ,
                        supportingText = { Text(text = goalHourError.orEmpty()) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmRequest,
                    enabled = subjectNameError == null && goalHourError == null
                ) {
                    Text("Save")
                }
            }
        )
    }
}
