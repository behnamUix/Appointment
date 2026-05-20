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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.ui.theme.navigation.Screen
import com.behnamuix.appointment.viewModel.appointment.AddAppointmentViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@Composable
fun AppointmentAddSc(
    navController: NavHostController,
    addVm: AddAppointmentViewModel = koinViewModel()
) {
    val personId = navController.currentBackStackEntry?.arguments?.getString("personId")

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

        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
    val calendarStart = Calendar.getInstance()
    val calendarEnd = Calendar.getInstance()

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
                                val unixTimeMillis = calendarStart.timeInMillis
                                Log.d("TIME-S",unixTimeMillis.toString())
                                onDateTimePicked(unixTimeMillis)

                                addVm.selectedStartDate.value = unixTimeMillis.toInt()
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
            color = MaterialTheme.colorScheme.secondary,
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
                                val unixTimeMillis = calendarEnd.timeInMillis
                                Log.d("TIME-E",unixTimeMillis.toString())

                                onDateTimePicked(unixTimeMillis)

                                addVm.selectedEndDateText.value = unixTimeMillis.toInt()
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
}