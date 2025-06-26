package com.example.smartstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.example.smartstudy.ui.theme.NavGraphs
import com.example.smartstudy.ui.theme.SmartStudyTheme
import com.example.smartstudy.ui.theme.domain.model.Session
import com.example.smartstudy.ui.theme.domain.model.Subject
import com.example.smartstudy.ui.theme.domain.model.Task
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge layout (like transparent status and nav bars)
        enableEdgeToEdge()

        setContent {
            SmartStudyTheme {
                    DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

val subjects = listOf(
    Subject(
        name = "Hindi", goalHours = 10f, colors = Subject.subjectCardColors[0].map { it.toArgb() },
        subjectId =1),
    Subject(
        name = "Math", goalHours = 10f, colors = Subject.subjectCardColors[1].map{it.toArgb()},
        subjectId =1),
    Subject(
        name = "Science", goalHours = 10f, colors = Subject.subjectCardColors[2].map{it.toArgb()},
        subjectId =1),
    Subject(
        name = "English", goalHours = 10f, colors = Subject.subjectCardColors[3].map{it.toArgb()},
        subjectId =1),
    Subject(
        name = "History", goalHours = 10f, colors = Subject.subjectCardColors[4].map{it.toArgb()},
        subjectId =1)
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
