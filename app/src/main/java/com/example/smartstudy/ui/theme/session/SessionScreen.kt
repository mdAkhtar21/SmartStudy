package com.example.smartstudy.ui.theme.session

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartstudy.sessions
import com.example.smartstudy.subjects
import com.example.smartstudy.ui.theme.Components.DeleteDialog
import com.example.smartstudy.ui.theme.Components.SubjectListBottomSheet
import com.example.smartstudy.ui.theme.Components.studySessionList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun SessionScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel:SessionViewModel= hiltViewModel()
    sessionScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun sessionScreen(
    onBackButtonClick: () -> Unit
) {
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    val sheetState= rememberModalBottomSheetState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val scope= rememberCoroutineScope()


    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        Subjects = subjects,
        onSubjectClicked = {
            scope.launch {
                sheetState.hide()
                if (!sheetState.isVisible) {
                    isBottomSheetOpen = false
                }
            }
        },
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
                if (!sheetState.isVisible) {
                    isBottomSheetOpen = false
                }
            }
        }
    )

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Session?",
        bodyText = "Are you sure, you want to delete this task? This action can not be undone",
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmRequest = { isDeleteDialogOpen = false }
    )

    Scaffold(
        topBar = {
            sessionTopBar(onBackButtonClick = onBackButtonClick)
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            item {
                TimerScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            item {
                RelatedScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    relatedToSubject = "English",
                    selectSubjectClick = {isBottomSheetOpen=true}
                )
            }
            item{
                ButtonSection(
                    modifier=Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    startButtonClick = {},
                    cancelButtonClick = {},
                    finishButtonClick = {}
                )
            }
            studySessionList(
                sectionTitle = "STUDY SESSIONS HISTORY",
                emptyListText = "You don't have any study sessions\nStart a study session to begin regarding your progress",
                session = sessions,
                onDeleteIconClick = {  isDeleteDialogOpen=true}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sessionTopBar(
    onBackButtonClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigation Back to Screen"
                )
            }
        },
        title = {
            Text(
                text = "Study Session",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    )
}

@Composable
fun TimerScreen(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .size(250.dp)
                .border(
                    5.dp, MaterialTheme.colorScheme.surfaceVariant,
                    CircleShape
                )
        )
        Text(
            text = "00:05:32",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp)
        )
    }
}

@Composable
fun RelatedScreen(
    modifier: Modifier,
    relatedToSubject: String,
    selectSubjectClick: () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Related to subject",
            style = MaterialTheme.typography.bodySmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = relatedToSubject,
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = selectSubjectClick) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Subject"
                )
            }
        }
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier,
    startButtonClick: () -> Unit,
    cancelButtonClick: () -> Unit,
    finishButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = cancelButtonClick) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Cancel"
            )
        }
        Button(onClick = startButtonClick) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Start"
            )
        }
        Button(onClick = finishButtonClick) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Finish"
            )
        }
    }
}
