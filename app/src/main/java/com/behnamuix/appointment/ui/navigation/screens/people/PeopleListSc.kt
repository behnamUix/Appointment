package com.behnamuix.appointment.ui.theme.navigation.screens.people

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.data.local.model.FakePeople
import com.behnamuix.appointment.data.remote.remoteModel.people.PeopleData
import com.behnamuix.appointment.ui.navigation.screens.appointment.AppointmentCard
import com.behnamuix.appointment.ui.navigation.screens.appointment.ToolbarComp
import com.behnamuix.appointment.ui.theme.navigation.Screen
import com.behnamuix.appointment.viewModel.appointment.AppointmentListViewModel
import com.behnamuix.appointment.viewModel.people.PeopleListViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun PeopleListSc(
    navController: NavHostController,
    peopleVm: PeopleListViewModel = koinViewModel(),
    listVm: AppointmentListViewModel = koinViewModel(),
    onItemClick: (Int) -> Unit
) {
    var list = peopleVm.peopleList.collectAsState()
    LaunchedEffect(Unit) {

        peopleVm.loadPeopleList()

    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(16.dp))
        ToolbarComp(
            "People list",
            onBackClick = {
                navController.popBackStack()
            },
            onAddClick = { navController.navigate(Screen.PeopleAdd.route) })
        Spacer(modifier = Modifier.height(16.dp))

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (list.value?.success ?: false) {
                LazyColumn {
                    items(list.value?.data?.data ?: emptyList()) {
                        PeopleCard(it) {
                            onItemClick(it.id)
                            listVm.itemId.intValue = it.id
                        }

                    }
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.primary.copy(0.5f)
                        )
                        Text(
                            "Loading , please wait...",
                            color = MaterialTheme.colorScheme.onPrimary.copy(0.4f),
                            textAlign = TextAlign.Center,

                            )
                    }

                }

            }


        }
    }
}

@Composable
fun PeopleCard(people: PeopleData, onCardClick: () -> Unit) {
    OutlinedCard(modifier = Modifier.padding(8.dp), onClick = { onCardClick() }) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.icon_apointment),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "  " + people.appointmentsCount.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
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
                        "${people.firstName} ${people.lastName}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        people.phoneNumber,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                    )
                    Text(
                        people.socialNumber,
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
                        painter = painterResource(R.drawable.icon_delete),
                        contentDescription = ""
                    )
                }
            }


        }

    }
}