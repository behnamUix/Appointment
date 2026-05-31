package com.behnamuix.appointment.ui.theme.navigation.screens.appointment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponseGetAppointment
import com.behnamuix.appointment.ui.theme.navigation.Screen
import com.behnamuix.appointment.utils.dateTimeFormat
import com.behnamuix.appointment.viewModel.appointment.AddAppointmentViewModel
import com.behnamuix.appointment.viewModel.appointment.AppointmentGetViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar
import java.util.TimeZone

@Composable
fun AppointmentAddSc(
    navController: NavHostController,
    addVm: AddAppointmentViewModel = koinViewModel(),
    getVm: AppointmentGetViewModel = koinViewModel(),
) {
    var update by remember{mutableStateOf(false)}
    var personId = navController.currentBackStackEntry?.arguments?.getString("personId")
    val appointmentId = navController.currentBackStackEntry?.arguments?.getString("appointmentId")

    val appointment by getVm.appointment.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        addVm.checkValue()
        addVm.showToast.collect {
            if (it) {
                Toast.makeText(context, addVm.toastMsg.value, Toast.LENGTH_SHORT).show()
            }

        }


    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),


        ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Appointment Add",
            style = MaterialTheme.typography.titleLarge,

            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            appointmentId?.toInt()?.let {
                if (it > -1) {
                    getVm.getAppointment(it)
                    UpdateAppointment(addVm, appointment) {
                        personId = it
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.PeopleList.route)
                }) {

                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(R.drawable.icon_user),
                    contentDescription = ""
                )
                Icon(
                    modifier = Modifier
                        .width(300.dp)
                        .align(Alignment.End),
                    painter = painterResource(R.drawable.icon_add),
                    contentDescription = ""
                )
                Text(
                    "select person",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            MyTextField("title", addVm.title)
            MyTextField("desc", addVm.desc, desc = true)

            PickDateTimeComp(addVm) {
                Log.d("TAG", it.toString())
            }
            if (update) {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = {

                        addVm.addAppointment(
                            personId
                        )

                        //harchi navigation ghabl az list bood remove mishe
                        navController.navigate(Screen.AppointmentList.route) {
                            popUpTo(Screen.AppointmentList.route) {
                                inclusive = true
                            }
                        }


                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "update",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = {

                        addVm.addAppointment(
                            personId
                        )

                        //harchi navigation ghabl az list bood remove mishe
                        navController.navigate(Screen.AppointmentList.route) {
                            popUpTo(Screen.AppointmentList.route) {
                                inclusive = true
                            }
                        }


                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "save",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            TextButton(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "back",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }


    }
}

@Composable
fun UpdateAppointment(
    addVm: AddAppointmentViewModel,
    appointment: ApiResponseGetAppointment?,
    setPersonId: (String) -> Unit
) {

    addVm.title.value = appointment?.data?.title ?: ""
    addVm.desc.value = appointment?.data?.description ?: ""
    addVm.startDate.value = (appointment?.data?.startTime ?: "") as String
    addVm.endDate.value = (appointment?.data?.endTime ?: "") as String
    addVm.selectedStartDate.value = (appointment?.data?.startTime ?: "") as Int
    addVm.selectedEndDate.value = (appointment?.data?.endTime ?: "") as Int
    setPersonId(appointment?.data?.personId.toString())
}

@Composable
fun MyTextField(label: String, value: MutableState<String>, desc: Boolean = false) {
    if (desc) {
        OutlinedTextField(

            value = value.value,
            onValueChange = { value.value = it },
            label = { Text(label) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            minLines = 4,
            maxLines = 6
        )
    } else {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },

            shape = RoundedCornerShape(16.dp),
            value = value.value, onValueChange = { value.value = it })
    }

}

@Composable
fun PickDateTimeComp(
    addVm: AddAppointmentViewModel,
    onDateTimePicked: (Long) -> Unit
) {
    val context = LocalContext.current
    val tehranTimeZone = TimeZone.getTimeZone("Asia/Tehran")

    val calendarStart = Calendar.getInstance(tehranTimeZone)
    val calendarEnd = Calendar.getInstance(tehranTimeZone)



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            OutlinedButton(
                modifier = Modifier.size(110.dp),
                shape = CircleShape,
                onClick = {
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val timePicker = TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    calendarStart.set(year, month, dayOfMonth, hour, minute, 0)
                                    //converter
                                    val startUnixTimeMillis = calendarStart.timeInMillis.toLong()
                                    addVm.startDate.value = dateTimeFormat(startUnixTimeMillis)
                                    Log.d("TIME-S", startUnixTimeMillis.toString())
                                    onDateTimePicked(startUnixTimeMillis)

                                    addVm.selectedStartDate.value =
                                        (startUnixTimeMillis / 1000L).toInt()
                                },
                                calendarStart.get(Calendar.HOUR_OF_DAY),
                                calendarStart.get(Calendar.MINUTE),
                                true
                            )
                            timePicker.show()
                        },
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.show()
                }) {
                Text(" start date", textAlign = TextAlign.Center)
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(6.dp)
            )

            OutlinedButton(

                shape = CircleShape,
                modifier = Modifier.size(90.dp),
                onClick = {
                    //date dialog
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            //time dialog
                            val timePicker = TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    calendarEnd.set(year, month, dayOfMonth, hour, minute, 0)
                                    //converter to unix time
                                    val endUnixTimeMillis = calendarEnd.timeInMillis
                                    //format
                                    addVm.endDate.value = dateTimeFormat(endUnixTimeMillis)
                                    Log.d("TIME-E", endUnixTimeMillis.toString())

                                    onDateTimePicked(endUnixTimeMillis)

                                    addVm.selectedEndDate.value =
                                        (endUnixTimeMillis / 1000L).toInt()
                                },
                                calendarEnd.get(Calendar.HOUR_OF_DAY),
                                calendarEnd.get(Calendar.MINUTE),
                                true
                            )
                            timePicker.show()
                        },
                        calendarEnd.get(Calendar.YEAR),
                        calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.show()
                }) {
                Text(" end date", textAlign = TextAlign.Center)
            }
        }

        Text(
            addVm.startDate.value.toString(),
            style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary
        )
        Text(
            addVm.endDate.value,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )


    }

}