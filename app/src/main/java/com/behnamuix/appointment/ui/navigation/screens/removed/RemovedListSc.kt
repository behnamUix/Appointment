package com.behnamuix.appointment.ui.theme.navigation.screens.removed

import android.widget.Toast
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteModel.appointment.Item
import com.behnamuix.appointment.data.remote.remoteModel.people.ApiResponsePeople
import com.behnamuix.appointment.data.remote.remoteModel.people.PeopleData
import com.behnamuix.appointment.utils.setMessage
import com.behnamuix.appointment.viewModel.appointment.AppointmentListViewModel
import com.behnamuix.appointment.viewModel.removed.RemovedListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RemovedListSc(
    navController: NavHostController,
    removedVm: RemovedListViewModel = koinViewModel(),
    listVm: AppointmentListViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val appointmentList = removedVm.removedList.collectAsState()
    val peopleList = removedVm.peopleRemovedList.collectAsState()
    val pagerState = rememberPagerState(pageCount = { removedVm.tabs.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        removedVm.loadRemovedList()
        removedVm.loadPeopleRemovedList()
        removedVm.showToast.collect {
            if (it) {
                Toast.makeText(context, removedVm.msg.value, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ToolbarComp("Removed list", onAddClick = {}) {
            navController.popBackStack()
        }
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                selected = pagerState.currentPage == 1,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(0, animationSpec = tween(500)) }
                },
                text = { Text("appointment") }
            )
            Tab(
                selected = pagerState.currentPage == 0,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(1, animationSpec = tween(500)) }
                },
                text = { Text("people") }
            )
        }

        Spacer(Modifier.height(16.dp))
        //for swipe between tabs
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> AppointmentTabContent(appointmentList, listVm, removedVm)

                1 -> PeopleTabContent(peopleList, listVm, removedVm)
            }
        }

    }


}

@Composable
fun AppointmentTabContent(
    listA: State<ApiResponse?>,
    listVm: AppointmentListViewModel,
    removedVm: RemovedListViewModel
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (listA.value?.success ?: false) {
            LazyColumn {
                items(listA.value?.data?.data ?: emptyList()) {
                    AppointmentRemovedCard(it) {
                        removedVm.restoreAppointment(it.id)
                        setMessage("appointment restored!", removedVm.msg)
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

                if (listVm.connectStatus.value) {
                    Text(
                        "offline!",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.6f),
                        textAlign = TextAlign.Center,

                        )
                }
            }

        }


    }
}

@Composable
fun PeopleTabContent(
    listP: State<ApiResponsePeople?>,
    listVm: AppointmentListViewModel,
    removedVm: RemovedListViewModel
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (listP.value?.success ?: false) {
            LazyColumn {
                items(listP.value?.data?.data ?: emptyList()) {
                    PeopleRemovedCard(it) {
                        removedVm.restorePeople(it.id)
                        setMessage("people restored!", removedVm.msg)
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

                if (listVm.connectStatus.value) {
                    Text(
                        "offline!",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.6f),
                        textAlign = TextAlign.Center,

                        )
                }
            }

        }


    }
}

@Composable
fun AppointmentRemovedCard(item: Item, onItemClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .alpha(0.6f)
    ) {
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
                        item.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        item.phoneNumber,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                    )
                }
                Spacer(Modifier.weight(1f))
                IconButton({
                    onItemClick()
                }) {
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
                item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                item.description,
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
                        text = item.startTime,
                        textAlign = TextAlign.Center,

                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.5f)
                    )

                    Text(
                        modifier = Modifier.weight(1f),

                        text = item.endTime,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(0.5f)
                    )

                }
            }

        }

    }
}

@Composable
fun PeopleRemovedCard(people: PeopleData, deleteItem: () -> Unit) {
    OutlinedCard(
        modifier =
            Modifier
                .padding(8.dp)
                .alpha(0.6f)
    ) {
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
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "${people.firstName} ${people.lastName}",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
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
                IconButton({
                    deleteItem()
                }) {
                    Icon(
                        tint = Color(0xFFF44336),
                        modifier = Modifier
                            .size(32.dp),
                        painter = painterResource(R.drawable.icon_restore),
                        contentDescription = ""
                    )
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
                tint = MaterialTheme.colorScheme.onPrimary,
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
            Image(
                painter = painterResource(R.drawable.icon_api),
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }

    }

}