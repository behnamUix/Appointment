package com.behnamuix.appointment.ui.navigation.screens.appointment

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.data.remote.remoteModel.appointment.Item
import com.behnamuix.appointment.ui.theme.navigation.Screen
import com.behnamuix.appointment.utils.setMessage
import com.behnamuix.appointment.viewModel.appointment.AppointmentListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppointmentListSc(
    navController: NavHostController,
    vm: AppointmentListViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val list = vm.appointmentList.collectAsState()
    LaunchedEffect(Unit) {
        vm.appointmentLoad()
        vm.checkInternet(ctx = context)
        vm.showToast.collect {
            if (it) {
                Toast.makeText(context, vm.msg.value, Toast.LENGTH_SHORT).show()
            }
        }

    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(16.dp))
        ToolbarComp(

            "Appointment list", onBackClick = {
                navController.popBackStack()
            },
            onAddClick = {
                navController.navigate(Screen.AppointmentAdd.route)
            })
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (list.value?.success ?: false) {
                LazyColumn() {
                    items(list.value?.data?.data ?: emptyList()) {
                        AppointmentCard(it) {
                            vm.itemId.intValue = it.id
                            vm.openAlertDialog.value = true
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

                    if (!vm.connectStatus.value) {
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
        if (vm.openAlertDialog.value) {
            DeleteDialogComp(
                "delete reason",
                vm
            ) {
                vm.openAlertDialog.value = it

            }
        }
    }

}

@Composable
fun AppointmentCard(item: Item, deleteItem: () -> Unit) {
    OutlinedCard(modifier = Modifier.padding(8.dp)) {
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
                        item.personName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                    )
                    Text(
                        item.phoneNumber,
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
                        painter = painterResource(R.drawable.icon_delete),
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
fun DeleteDialogComp(
    label: String,
    vm: AppointmentListViewModel,
    dialog: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("example reason!") }
    val id = vm.itemId.value
    AlertDialog(
        title = {
            Text(text = "warning")
        },
        text = {
            OutlinedTextField(
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                value = text, onValueChange = { text = it })
        },
        onDismissRequest = {

        },
        confirmButton = {
            TextButton(
                onClick = {
                    vm.appointmentDelete(id, text)
                    dialog(false)
                    setMessage("appointment deleted!",vm.msg)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dialog(false)
                }
            ) {
                Text("Dismiss")
            }
        })
}

@Composable
fun ToolbarComp(
    title: String,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit
) {
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





