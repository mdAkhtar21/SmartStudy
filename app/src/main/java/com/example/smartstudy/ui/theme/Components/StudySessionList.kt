package com.example.smartstudy.ui.theme.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.smartstudy.R
import com.example.smartstudy.ui.theme.domain.model.Session

fun LazyListScope.studySessionList(
    sectionTitle: String,
    session: List<Session>,
    emptyListText: String,
    onDeleteIconClick: (Session) -> Unit


) {
    item {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }

    if (session.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.img_lamp),
                    contentDescription = emptyListText
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = emptyListText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        items(session) { session ->
            StudySessionCard(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                session=session,
                onDeleteIconClick={onDeleteIconClick(session)}
            )
        }
    }
}

@Composable
fun StudySessionCard(
    modifier: Modifier = Modifier,
    session: Session,
    onDeleteIconClick:()->Unit
) {
    ElevatedCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (modifier=Modifier.padding(start = 5.dp)){
                Text(
                    text =session.relatedToSubject,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "${session.date}",
                    style = MaterialTheme.typography.bodySmall
                )

            }
            Spacer(modifier=Modifier.weight(1f))
            Text(
                text = "${session.duration} hr",
                style = MaterialTheme.typography.bodySmall
            )
            IconButton(onClick = onDeleteIconClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Session"
                )
            }
        }
    }
}
