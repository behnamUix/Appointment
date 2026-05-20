package com.behnamuix.appointment.ui.theme.navigation.screens.people

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.ui.theme.navigation.Screen
import com.behnamuix.appointment.utils.setMessage
import com.behnamuix.appointment.viewModel.people.AddPeopleViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PeopleAddSc(
    navController: NavHostController,
    addPeopleVm: AddPeopleViewModel = koinViewModel()

) {
    val showError = addPeopleVm.error
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        addPeopleVm.showToast.collect {
            if (it) {
                Toast.makeText(context, addPeopleVm.msg.value, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp),


        ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "People Add",
            style = MaterialTheme.typography.titleLarge,

            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(24.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.icon_user),
                contentDescription = ""
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MyNameTextField("firstName", addPeopleVm.firestName, showError = showError)
                MyNameTextField("lastName", addPeopleVm.lastName, showError = showError)
            }
            MyPhoneTextField("phone.No", addPeopleVm.phoneNumber, showError = showError)
            SocialNumberTextField("social number", addPeopleVm.socialNumber, showError = showError)
            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    addPeopleVm.addPeople()
                    setMessage("people added", addPeopleVm.msg)
                    //navController.popBackStack()


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
fun MyNameTextField(
    label: String,
    addVm: MutableState<String>,
    showError: State<Boolean>
) {
    OutlinedTextField(
        maxLines = 1,
        isError = showError.value,
        label = { Text(label) },
        modifier = Modifier.width(200.dp),
        shape = RoundedCornerShape(16.dp),
        value = addVm.value, onValueChange = { addVm.value = it })

}

@Composable
fun MyPhoneTextField(
    label: String,
    addVm: MutableState<String>,
    showError: State<Boolean>
) {
    OutlinedTextField(
        leadingIcon = {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.icon_phone),
                contentDescription = ""
            )
        },
        isError = showError.value,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        value = addVm.value, onValueChange = { if(addVm.value.length<=11){addVm.value = it} })


}

@Composable
fun SocialNumberTextField(
    label: String,
    addVm: MutableState<String>, showError: State<Boolean>
) {
    OutlinedTextField(
        leadingIcon = {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.icon_social_number),
                contentDescription = ""
            )
        },
        isError = showError.value,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        value = addVm.value, onValueChange = { if(addVm.value.length<=10){addVm.value = it} })
}
