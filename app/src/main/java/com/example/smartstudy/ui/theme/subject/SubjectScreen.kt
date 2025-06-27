package com.example.smartstudy.ui.theme.subject

import AddSubjectDialog
import CardCount
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartstudy.sessions
import com.example.smartstudy.task
import com.example.smartstudy.ui.theme.Components.DeleteDialog
import com.example.smartstudy.ui.theme.Components.studySessionList
import com.example.smartstudy.ui.theme.Components.taskList
import com.example.smartstudy.ui.theme.destinations.TaskScreenRouteDestination
import com.example.smartstudy.ui.theme.domain.model.Subject
import com.example.smartstudy.ui.theme.task.TaskScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class SubjectScreenNavArgs(
    val subjectId:Int
)

@Destination(navArgsDelegate = SubjectScreenNavArgs::class)
@Composable
fun SubjectScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel:SubjectViewModel= hiltViewModel()
    SubjectScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onBackButtonClick = {
            navigator.navigateUp()
        },
        onAddTaskButtonClick = {
            val navArgs = TaskScreenNavArgs(taskId = null, subjectId = -1)
            navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
        },
        onTaskCardClick = { taskid ->
            val navArgs = TaskScreenNavArgs(taskId = taskid, subjectId = null)
            navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SubjectScreen(
    onBackButtonClick:()->Unit,
    onAddTaskButtonClick:()->Unit,
    onTaskCardClick: (Int?)->Unit
) {
    val listState = rememberLazyListState()
    val isFABExtended by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }

    var isAddSubjectBoxOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogbox by rememberSaveable { mutableStateOf(false) }

    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }

    AddSubjectDialog(
        isOpen = isAddSubjectBoxOpen,
        subjectName = subjectName,
        onSubjectNameChange = { subjectName = it },
        goalHour = goalHour,
        onGoalHourChange = { goalHour = it },
        selectedColor = selectColor,
        onColorChange = { selectColor = it },
        onDismissRequest = { isAddSubjectBoxOpen = false },
        onConfirmRequest = { isAddSubjectBoxOpen = false }
    )
    DeleteDialog(
        isOpen = isDeleteSubjectDialogOpen,
        title = "Delete the Session",
        bodyText = "Are you sure , you want to delete this Subject, task and study session is permanently ,This Action can't be undo",
        onConfirmRequest = { isDeleteSubjectDialogOpen = false },
        onDismissRequest = { isDeleteSubjectDialogOpen = false }
    )

    DeleteDialog(
        isOpen = isDeleteDialogbox,
        title = "Delete the Session",
        bodyText = "Are you sure , you want to delete this session Your study hour should be reduce, by session this time . This session Can't be Under",
        onConfirmRequest = { isDeleteDialogbox = false },
        onDismissRequest = { isDeleteDialogbox = false }
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectScreenTopBar(
                title = "hello",
                onBackButtonClick = onBackButtonClick,
                onDeleteButtonClick = { isDeleteSubjectDialogOpen = true },
                onEditButtonClick = { isEditSubjectDialogOpen = true },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTaskButtonClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                },
                text = { Text(text = "Add Task") },
                expanded = isFABExtended
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SubjectOverViewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    studieshours = "10",
                    goalHours = "15",
                    progess = .75f
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "Upcoming Tasks",
                emptyListText = "You don't have upcomming task\nClick the + on the Subject Screen",
                tasks = task,
                onCheckboxClick = {},
                onTaskCardClick = onTaskCardClick
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "Completed Task",
                emptyListText = "You don't have Completed task\nClick the Box on completed tasks",
                tasks = task,
                onCheckboxClick = {},
                onTaskCardClick = onTaskCardClick
            )
            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any study sessions\nStart a study session to begin regarding your progress",
                session = sessions,
                onDeleteIconClick = { isDeleteDialogbox = true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title: String,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            IconButton(onClick = onDeleteButtonClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Subject"
                )
            }
            IconButton(onClick = onEditButtonClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Subject"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun SubjectOverViewSection(
    modifier: Modifier,
    studieshours: String,
    goalHours: String,
    progess: Float
) {
    val percentageProgress = (progess * 100).toInt().coerceIn(0, 100)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardCount(
            modifier = Modifier.weight(1f),
            headingtext = "Goal Study Hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CardCount(
            modifier = Modifier.weight(1f),
            headingtext = "Study Hours",
            count = studieshours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progess,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(text = "$percentageProgress%")
        }
    }
}
