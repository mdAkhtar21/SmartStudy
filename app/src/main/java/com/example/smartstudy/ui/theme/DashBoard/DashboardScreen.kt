package com.example.smartstudy.ui.theme.DashBoard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartstudy.R
import com.example.smartstudy.ui.theme.Components.SubjectCard
import com.example.smartstudy.ui.theme.Components.studySessionList
import com.example.smartstudy.ui.theme.Components.taskList
import com.example.smartstudy.ui.theme.domain.model.Session
import com.example.smartstudy.ui.theme.domain.model.Subject
import com.example.smartstudy.ui.theme.domain.model.Task

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoardScreen() {
    val subjects = listOf(
        Subject(name = "Hindi", goalHour = 10f, color = Subject.subjectCardColor[0],subjectId=1),
        Subject(name = "Math", goalHour = 10f, color = Subject.subjectCardColor[1],subjectId=1),
        Subject(name = "Science", goalHour = 10f, color = Subject.subjectCardColor[2],subjectId=1),
        Subject(name = "English", goalHour = 10f, color = Subject.subjectCardColor[3],subjectId=1),
        Subject(name = "History", goalHour = 10f, color = Subject.subjectCardColor[4],subjectId=1)
    )
    val task= listOf(
        Task(title = "Prepare Notes",
            Description="",
            dueDate=0L,
            Priority=1,
            releatedToSubject="",
            isComplete=false,
            taskSubjectId=0,
            taskid=1
            ),
        Task(title = "Complete Notes",
            Description="",
            dueDate=0L,
            Priority=2,
            releatedToSubject="",
            isComplete=true,
            taskSubjectId=0,
            taskid=1
        ),
        Task(title = "Prepare Notes",
            Description="",
            dueDate=0L,
            Priority=1,
            releatedToSubject="",
            isComplete=false,
            taskSubjectId=0,
            taskid=1
        ),
        Task(title = "Prepare Notes",
            Description="",
            dueDate=0L,
            Priority=1,
            releatedToSubject="",
            isComplete=false,
            taskSubjectId=0,
            taskid=1
        ),
        Task(title = "Prepare Notes",
            Description="",
            dueDate=0L,
            Priority=1,
            releatedToSubject="",
            isComplete=false,
            taskSubjectId=0,
            taskid=1
        )
    )
    val sessions= listOf(
        Session(
            relatedToSubject = "Math",
            date = 0L,
            duration = 2,
            sessionSubjectId = 0,
            sessionId = 0
        ),
        Session(
            relatedToSubject = "Hindi",
            date = 0L,
            duration = 2,
            sessionSubjectId = 0,
            sessionId = 0
        ),
        Session(
            relatedToSubject = "English",
            date = 0L,
            duration = 2,
            sessionSubjectId = 0,
            sessionId = 0
        ),
        Session(
            relatedToSubject = "History",
            date = 0L,
            duration = 2,
            sessionSubjectId = 0,
            sessionId = 0
        )
    )

    Scaffold(
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
                    subjectCount = subjects.size,
                    studiesHour = "10",
                    goalHour = "5"
                )
            }
            item {
                SubjectCardsSelection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects
                )
            }
            item{
                Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 20.dp),
                    onClick = {}
                ) {
                    Text(text = "Start Study Session")
                }
            }
            taskList(
                sectionTitle="Upcoming Tasks",
                emptyListText="You don't have upcomming task\n"+"Click the + on the Subject Screen",
                tasks= task,
                onCheckboxClick = {},
                onTaskCardClick = {}
            )
            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any study sessions\n"+"Start a study session to begin regarding your progress",
                session = sessions,
                onDeleteIconClick = {}
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
    emptyListText: String = "You have to add new subjects\nto see them here"
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
            IconButton(onClick = {}) {
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
                        gradientColor = subject.color,
                        onClick = {}
                    )
                }
            }
        }
    }
}

