package com.example.gymapp.Appearance.Views.Activities

import android.annotation.SuppressLint
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.ViewModels.Activities.ActivitiesViewModel
import com.example.gymapp.GymApi.ViewModels.Auth.AuthState
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getString
import com.example.gymapp.Appearance.Views.Dialog.ToastMessage
import com.example.gymapp.R
import kotlinx.coroutines.delay

@Composable
fun Activities(navController: NavHostController, authViewModel: AuthViewModel, activitiesViewModel: ActivitiesViewModel){

    val context = LocalContext.current

    val authState = authViewModel.authState.collectAsState()
    val accessToken by activitiesViewModel.accessToken.collectAsState()
    val activities by activitiesViewModel.activities.collectAsState()
    val userActivities by activitiesViewModel.userActivities.collectAsState()


    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    // Titulos de las paginas
    val tabs = listOf( context.getString(R.string.all_activities_word), context.getString(R.string.my_activities_word))

     LaunchedEffect (authState.value){
        when(authState.value){
            is AuthState.Unauthenticated ->
            {
                Toast.makeText(context, context.getString(R.string.unathenticated_error), Toast.LENGTH_SHORT).show();
                navController.navigate(Routes.Login.route)
            }

            is AuthState.Error -> {val messageResId = ToastMessage.getStringResourceId((authState.value as AuthState.Error).errorType)
                Toast.makeText(context, getString(context,messageResId), Toast.LENGTH_SHORT).show()
                authViewModel.signout()}
            else -> Unit
        }

    }



    LaunchedEffect(accessToken) {
        accessToken?.let {
            activitiesViewModel.getAllActivities()
            activitiesViewModel.getUserActivities()
        }
    }



    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(90.dp))
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    indicator = { tabPositions ->
                        TabRowDefaults.apply {
                            HorizontalDivider(
                                Modifier
                                    .height(2.dp)
                                    .padding(horizontal = 16.dp)
                                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    divider = {}
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            icon = {
                                when (index) {
                                    0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                    1 -> Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = null)
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) {
                0 -> AllActivitiesScreen(snackbarHostState, activities, activitiesViewModel)
                1 -> AllUserActivitiesScreen(snackbarHostState, userActivities!!, activitiesViewModel)
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun AllActivitiesScreen(snackbarHostState:SnackbarHostState, activities : List<ActivityResponse>?, activitiesViewModel: ActivitiesViewModel) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = context.getString(R.string.all_activities_word), fontSize = 25.sp)

        if (activitiesViewModel.activities.value == null) {
            // Muestra una barra circular mientras cargan actividades
            CircularProgressIndicator()
        }
        else if (activitiesViewModel.activities.value!!.isEmpty()) {
            Text(context.getString(R.string.not_activities_in_list_message))
        }
        else {
            //Muestra todas las actividades
            LazyColumn {
                items(activities!!) { activity ->
                    ShowActivity(
                        activity,
                        Icons.Default.Star,
                        context.getString(R.string.signup_word ),
                    ) {
                        coroutineScope.launch {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    context.getString(R.string.sing_up_activity_message) + " " + activity.title ,
                                    duration = SnackbarDuration.Long
                                )
                            }

                            activitiesViewModel.createParticipation(activity.id.toString())
                        }
                    }
                }
            }
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AllUserActivitiesScreen(snackbarHostState:SnackbarHostState, userActivities: List<ActivityResponse>?, activitiesViewModel: ActivitiesViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = context.getString(R.string.my_activities_word), fontSize = 25.sp)
        if (activitiesViewModel.userActivities.value == null) {
            CircularProgressIndicator()
        }else if(activitiesViewModel.userActivities.value!!.isEmpty()){
            Text(context.getString(R.string.not_activities_in_list_message))
        }else{
            LazyColumn {
                items(userActivities!!) { activity ->
                    key(activity) {
                        var isVisible by remember { mutableStateOf(true) }

                        AnimatedVisibility(
                            visible = isVisible,
                            exit = fadeOut(animationSpec = tween(durationMillis = 500))
                        ) {
                            ShowActivity(
                                activity,
                                Icons.Default.Delete,
                                context.getString(R.string.delete_word),
                            ) {
                                coroutineScope.launch {
                                    activity.id?.let {

                                        coroutineScope.launch {

                                            snackbarHostState.showSnackbar(
                                                context.getString(R.string.delete_activity_message) + " " + activity.title ,
                                                duration = SnackbarDuration.Long
                                            )
                                        }

                                        isVisible = false
                                        delay(500)
                                        activitiesViewModel.deleteParticipation(it)


                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ShowActivity(
    activity: ActivityResponse,
    buttonIcon: ImageVector,
    textButton: String,
    onClickAction: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = activity.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(imageVector = Icons.Default.Description, contentDescription = "Description Icon", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = activity.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Start Date Icon", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = activity.startDateTime,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "End Date Icon", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = activity.endDateTime,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClickAction,
                modifier =  Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(imageVector = buttonIcon, contentDescription = "Button Icon", tint = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = textButton, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }

}


