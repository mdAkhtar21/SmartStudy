package com.example.smartstudy.ui.theme.DashBoard
import AddSubjectDialog
import CardCount
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartstudy.R
import com.example.smartstudy.sessions
import com.example.smartstudy.task
import com.example.smartstudy.ui.theme.Components.DeleteDialog
import com.example.smartstudy.ui.theme.Components.SubjectCard
import com.example.smartstudy.ui.theme.Components.studySessionList
import com.example.smartstudy.ui.theme.Components.taskList
import com.example.smartstudy.ui.theme.Util.SnackbarEvent
import com.example.smartstudy.ui.theme.destinations.SessionScreenRouteDestination
import com.example.smartstudy.ui.theme.destinations.SubjectScreenRouteDestination
import com.example.smartstudy.ui.theme.destinations.TaskScreenRouteDestination
import com.example.smartstudy.ui.theme.domain.model.Subject
import com.example.smartstudy.ui.theme.task.TaskScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DashBoardScreenRoute(
    navigator: DestinationsNavigator
) {
    val viewModel:DashboardViewModel= hiltViewModel()
    val state by viewModel.state.collectAsState()
    DashBoardScreen(
        state=state,
        onEvent = viewModel::onEvent,
        onSubjectCardClick = { subjectId ->
            subjectId?.let {
                navigator.navigate(SubjectScreenRouteDestination(subjectId = it))
            }
        },
        onTaskCardClick = {taskid->
            val navArgs=TaskScreenNavArgs(taskId=taskid,subjectId=null)
            navigator.navigate(TaskScreenRouteDestination(navArgs=navArgs))
        },
        onSessionButtonclick = {
            navigator.navigate(SessionScreenRouteDestination())
        }
    )
}


@Composable
fun DashBoardScreen(
    state:DashboardState,
    onEvent: (DashboardEvent)->Unit,
    onSubjectCardClick:(Int?)->Unit,
    onTaskCardClick:(Int?)->Unit,
    onSessionButtonclick: () -> Unit
) {


    var isAddSubjectBoxOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogbox by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        snackbarEvent.collectLatest { event ->
            when(event) {
                is SnackbarEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }

                SnackbarEvent.NavigateUp -> {}
            }
        }
    }



    AddSubjectDialog(
        isOpen = isAddSubjectBoxOpen,
        subjectName = state.subjectName,
        onSubjectNameChange = { onEvent(DashboardEvent.OnSubjectNameChange(it))},
        goalHour = state.goalStudyHours,
        onGoalHourChange = {onEvent(DashboardEvent.OnGoalStudyHoursChange(it))},
        selectedColor = state.subjectCardColors,
        onColorChange = {onEvent(DashboardEvent.OnSubjectCardColorChange(it))},
        onDismissRequest = { isAddSubjectBoxOpen = false },
        onConfirmRequest = {
            onEvent(DashboardEvent.SaveSubject)
            isAddSubjectBoxOpen = false }
    )
    DeleteDialog(
        isOpen=isDeleteDialogbox,
        title="Delete the Session",
        bodyText = "Are you sure , you want to delete this session Your study hour should be reduce,"+
                "by session this time . This session Can't be Under",
        onConfirmRequest = {
            onEvent(DashboardEvent.DeleteSession)
            isDeleteDialogbox=false},
        onDismissRequest = {isDeleteDialogbox=false}
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { DashBoardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CardCountSelection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = state.totalSubjectCount,
                    studiesHour = state.totalStudiedHours.toString(),
                    goalHour = state.totalGoalStudyHours.toString()
                )
            }
            item {
                SubjectCardsSelection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = state.subjects,
                    onAddIcon = {
                        isAddSubjectBoxOpen=true
                    },
                    onSubjectCardClick = onSubjectCardClick
                )
            }
            item{
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp, vertical = 20.dp),
                    onClick = onSessionButtonclick
                ) {
                    Text(text = "Start Study Session")
                }
            }
            taskList(
                sectionTitle="Upcoming Tasks",
                emptyListText="You don't have upcomming task\n"+"Click the + on the Subject Screen",
                tasks= task,
                onCheckboxClick = {onEvent(DashboardEvent.OnTaskIsCompleteChange(it))},
                onTaskCardClick = onTaskCardClick
            )
            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any study sessions\n"+"Start a study session to begin regarding your progress",
                session = sessions,
                onDeleteIconClick = {
                    onEvent(DashboardEvent.OnDeleteSessionButtonClick(it))
                    isDeleteDialogbox =true}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "StudySmart",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
fun CardCountSelection(
    modifier: Modifier,
    subjectCount: Int,
    studiesHour: String,
    goalHour: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CardCount(
            modifier = Modifier.weight(1f),
            headingtext = "Subject Count",
            count = "$subjectCount"
        )
        CardCount(
            modifier = Modifier.weight(1f),
            headingtext = "Study Hours",
            count = studiesHour
        )
        CardCount(
            modifier = Modifier.weight(1f),
            headingtext = "Goal Study Hours",
            count = goalHour
        )
    }
}

@Composable
private fun SubjectCardsSelection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You have to add new subjects\nto see them here",
    onAddIcon:()->Unit,
    onSubjectCardClick: (Int?) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = onAddIcon) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }

        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp),
                painter = painterResource(id = R.drawable.img_books),
                contentDescription = emptyListText
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ) {
                items(subjectList) { subject ->
                    SubjectCard(
                        subjectName = subject.name,
                        gradientColor = subject.colors.map{Color(it)},
                        onClick = { onSubjectCardClick(subject.subjectId) }

                    )
                }
            }
        }
    }
}

