package com.behnamuix.appointment.ui.theme.navigation.screens.removed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.data.local.model.FakeAppointment

import com.behnamuix.appointment.viewModel.removed.RemovedListViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun RemovedListSc(
    navController: NavHostController,
    removedVm: RemovedListViewModel = koinViewModel()
) {
    var list = removedVm.removedList.collectAsState()
    LaunchedEffect(Unit) {
        delay(2000)
        removedVm.loadFakeList()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(16.dp))
        ToolbarComp (
            "Removed list", onBackClick = {
                navController.popBackStack()
            },
            onAddClick = {

            })

        Spacer(modifier = Modifier.height(16.dp))

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (list.value.isEmpty()) {
                Text(
                    "Loading , please wait...",
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.4f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                LazyColumn {
                    items(list.value) {
                        RemovedCard(it)

                    }
                }
            }


        }
    }
}

@Composable
fun ToolbarComp(title: String, onAddClick: () -> Unit, onBackClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton({
            onBackClick()
        }) {
            Icon(
                painter = painterResource(R.drawable.icon_arrow_back),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,

            )

        IconButton({
            onAddClick()
        }) {
            Icon(
                painter = painterResource(R.drawable.icon_add),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }

    }

}

@Composable
fun RemovedCard(appointment: FakeAppointment) {
    OutlinedCard(modifier = Modifier.padding(8.dp).alpha(0.5f)) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(shape = CircleShape) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp),
                        painter = painterResource(R.drawable.icon_person),
                        contentDescription = ""
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        appointment.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        appointment.phoneNumber,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                    )
                }
                Spacer(Modifier.weight(1f))
                IconButton({}) {
                    Icon(
                        tint = Color(0xFFF44336),
                        modifier = Modifier
                            .size(32.dp),
                        painter = painterResource(R.drawable.icon_restore),
                        contentDescription = ""
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
            )
            Text(
                appointment.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                appointment.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
            )
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = appointment.startTime,
                        textAlign = TextAlign.Center,

                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.5f)
                    )

                    Text(
                        modifier = Modifier.weight(1f),

                        text = appointment.endTime,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.5f)
                    )

                }
            }

        }

    }
}
