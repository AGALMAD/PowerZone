package com.example.gymapp.Appearance.Views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gymapp.R
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Data.SettingsDataStore
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.ViewModels.AuthState
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import kotlinx.coroutines.launch


@Composable
fun SettingsContent(navController: NavHostController, authViewModel : AuthViewModel){

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    //Necesita sesión iniciada para poder acceder a la configuración
    LaunchedEffect (authState.value){
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routes.Login.route)
            else -> Unit
        }
    }


    val scope = rememberCoroutineScope()

    val dataStore = SettingsDataStore(context)

    val contextForToast = LocalContext.current.applicationContext

    var radioButtonCheck by remember { mutableIntStateOf(1) }
    var checkBox1Check by remember {mutableStateOf(false)}
    var checkBox2Check by remember {mutableStateOf(false)}
    var checkBox3Check by remember {mutableStateOf(false)}
    var checkBox4Check by remember {mutableStateOf(false)}
    var checkBox5Check by remember {mutableStateOf(false)}
    var checkBox6Check by remember {mutableStateOf(false)}
    var checkBox7Check by remember {mutableStateOf(false)}
    var checkBox8Check by remember {mutableStateOf(false)}
    var switchCheck by remember { mutableStateOf(false)}
    var dropDownCheck by remember { mutableStateOf(context.getString(R.string.select_time_text))}


    //Obtiene todos los datos guardados en el Data Store
    LaunchedEffect(Unit) {
        dataStore.getRadioButtonPartOfBody.collect { value ->
            radioButtonCheck = value ?: 1
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxPecho.collect { value ->
            checkBox1Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxEspalda.collect { value ->
            checkBox2Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxHombro.collect { value ->
            checkBox3Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxBrazo.collect { value ->
            checkBox4Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxCuadriceps.collect { value ->
            checkBox5Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxFemoral.collect { value ->
            checkBox6Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxGemelos.collect { value ->
            checkBox7Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getCheckBoxGluteo.collect { value ->
            checkBox8Check = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getSwitchCardio.collect { value ->
            switchCheck = value ?: false
        }
    }
    LaunchedEffect(Unit) {
        dataStore.getSwitchCardioTime.collect { value ->
            dropDownCheck = value ?: context.getString(R.string.select_time_text)
        }
    }


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        GenerateTitle(context.getString(R.string.configuration_title))


        Image(
            painter = painterResource(R.drawable.configuracion),
            contentDescription = "Imagen Configuración",
            modifier = Modifier.size(250.dp)
        )

        GenerateText(context.getString(R.string.select_zone_text))

        Column(verticalArrangement = Arrangement.spacedBy(-18.dp, Alignment.CenterVertically)){
            GenerateRadioButton(context.getString(R.string.fullbody_text), 1, radioButtonCheck){ state -> radioButtonCheck = state}
            GenerateRadioButton(context.getString(R.string.upperbody_text), 2, radioButtonCheck) { state -> radioButtonCheck = state}
            GenerateRadioButton(context.getString(R.string.legs_text), 3, radioButtonCheck) { state -> radioButtonCheck = state}
        }

        GenerateText(context.getString(R.string.select_muscles_text))


        Column() {

            if(radioButtonCheck == 1){

                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                    Column {

                        GenerateCheckBox(context.getString(R.string.chest_text), checkBox1Check) { state -> checkBox1Check = state}
                        GenerateCheckBox(context.getString(R.string.back_text), checkBox2Check) { state -> checkBox2Check = state}
                        GenerateCheckBox(context.getString(R.string.quadriceps_text), checkBox5Check) { state -> checkBox5Check = state}
                        GenerateCheckBox(context.getString(R.string.hamstrings_text), checkBox6Check) { state -> checkBox6Check = state}
                    }

                    Column {

                        GenerateCheckBox(context.getString(R.string.shoulder_text), checkBox3Check) { state -> checkBox3Check = state}
                        GenerateCheckBox(context.getString(R.string.arm_text), checkBox4Check) { state -> checkBox4Check = state}
                        GenerateCheckBox(context.getString(R.string.calf_text), checkBox7Check) { state -> checkBox7Check = state}
                        GenerateCheckBox(context.getString(R.string.glute_text), checkBox8Check) { state -> checkBox8Check = state}
                    }



                }



            }

            //Muestra los musculos a entrenar según la parte del cuerpo seleccionada en los radio buttons
            if(radioButtonCheck == 2){

                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                    Column {

                        GenerateCheckBox(context.getString(R.string.chest_text), checkBox1Check) { state -> checkBox1Check = state}
                        GenerateCheckBox(context.getString(R.string.back_text), checkBox2Check) { state -> checkBox2Check = state}
                    }

                    Column {
                        GenerateCheckBox(context.getString(R.string.shoulder_text), checkBox3Check) { state -> checkBox3Check = state}
                        GenerateCheckBox(context.getString(R.string.arm_text), checkBox4Check) { state -> checkBox4Check = state}

                    }

                }



            }
            if(radioButtonCheck == 3){

                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)){
                    Column {

                        GenerateCheckBox(context.getString(R.string.quadriceps_text), checkBox5Check) { state -> checkBox5Check = state}
                        GenerateCheckBox(context.getString(R.string.hamstrings_text), checkBox6Check) { state -> checkBox6Check = state}
                    }

                    Column {
                        GenerateCheckBox(context.getString(R.string.calf_text), checkBox7Check) { state -> checkBox7Check = state}
                        GenerateCheckBox(context.getString(R.string.glute_text), checkBox8Check) { state -> checkBox8Check = state}

                    }

                }


            }
        }

        GenerateText(context.getString(R.string.select_cardio_text))


        //Switch junto a dropdown
        Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            GenerateSwitch("Cardio", switchCheck) {state -> switchCheck = state}
            if(switchCheck){
                GenerateDropDown(dropDownCheck) {state -> dropDownCheck = state}
            }
        }

        //Guarda los datos cuando se le da al botón Guardar
        Button(onClick = {

            if(radioButtonCheck == 3 && switchCheck){
                Toast.makeText(contextForToast, context.getString(R.string.text_error), Toast.LENGTH_SHORT).show()
            }
            else{
                scope.launch {
                    dataStore.setSwitchCardioTime(dropDownCheck)
                }
                scope.launch {
                    dataStore.setSwitchCardio(switchCheck)
                }
                scope.launch {
                    dataStore.setCheckBoxBrazo(checkBox4Check)
                }
                scope.launch {
                    dataStore.setCheckBoxPecho(checkBox1Check)
                }
                scope.launch {
                    dataStore.setCheckBoxEspalda(checkBox2Check)
                }
                scope.launch {
                    dataStore.setCheckBoxHombro(checkBox3Check)
                }
                scope.launch {
                    dataStore.setCheckBoxCuadriceps(checkBox5Check)
                }
                scope.launch {
                    dataStore.setCheckBoxFemoral(checkBox6Check)
                }
                scope.launch {
                    dataStore.setCheckBoxGemelos(checkBox7Check)
                }
                scope.launch {
                    dataStore.setCheckBoxGluteo(checkBox8Check)
                }
                scope.launch {
                    dataStore.setRadioButtonPartOfBody(radioButtonCheck)
                }
                Toast.makeText(contextForToast, context.getString(R.string.text_save), Toast.LENGTH_LONG).show()
                navController.navigate(Routes.Principal.route)
            }

        },
            shape = misFormas.large
        ) {
            Text(text = context.getString(R.string.save_button_text))
        }

    }
}



@Composable
fun GenerateTitle(title : String){
    Text(
        text = title,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
fun GenerateText(text : String){
    Text(
        text = text,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.headlineSmall,
    )
}


@Composable
fun GenerateRadioButton(
    textContent: String,
    id: Int,
    mutable: Int,
    lambda: (Int) -> Unit
){
    Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically){
        RadioButton(
            selected = mutable == id,
            onClick = {lambda(id)},
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedColor = MaterialTheme.colorScheme.primary
            ),
        )
        Text(
            text = textContent,
            modifier = Modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun GenerateCheckBox(
    textContent: String,
    mutable: Boolean,
    lambda: (Boolean) -> Unit
){
    Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = mutable,
            onCheckedChange = {lambda(!mutable)},
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                uncheckedColor = MaterialTheme.colorScheme.primary
            ),
        )
        Text(
            text = textContent,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun GenerateSwitch(
    textContent: String,
    mutable: Boolean,
    lambda: (Boolean) -> Unit
){
    Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = mutable,
            onCheckedChange = {lambda(!mutable)}
        )
        Text(
            text = textContent,
            modifier = Modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun GenerateDropDown(mutable:String,lambda: (String)->Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, color = MaterialTheme.colorScheme.onTertiaryContainer, shape = MaterialTheme.shapes.large)

                .width(150.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(modifier = Modifier.padding(10.dp),
                    text = mutable,
                    color = MaterialTheme.colorScheme.secondary,
                    style =MaterialTheme.typography.labelMedium )

                IconButton(
                    colors  = IconButtonDefaults.iconButtonColors(contentColor=MaterialTheme.colorScheme.secondary),
                    onClick = {
                        expanded = !expanded
                    }
                ) {
                    val icon = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    Icon(
                        icon,
                        contentDescription = if (expanded) "Close Menu" else "Open Menu"
                    )
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text("10m")
                },
                onClick = {
                    lambda("10m")
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("20m")
                },
                onClick = {
                    lambda("20m")
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("30m")
                },
                onClick = {
                    lambda("30m")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text("40m")
                },
                onClick = {
                    lambda("40m")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text("50m")
                },
                onClick = {
                    lambda("50m")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text("60m")
                },
                onClick = {
                    lambda("60m")
                    expanded = false
                }
            )
        }
    }
}