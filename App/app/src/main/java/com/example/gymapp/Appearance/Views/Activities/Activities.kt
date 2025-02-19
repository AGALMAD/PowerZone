package com.example.gymapp.Appearance.Views.Activities

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.List
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.ViewModels.Activities.ActivitiesViewModel
import com.example.gymapp.GymApi.ViewModels.Auth.AuthState
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults

@Composable
fun Activities(navController: NavHostController, authViewModel: AuthViewModel, activitiesViewModel: ActivitiesViewModel){
    val authState = authViewModel.authState.collectAsState()
    val accessToken by activitiesViewModel.accessToken.collectAsState()
    val activities by activitiesViewModel.activities.collectAsState()
    val userActivities by activitiesViewModel.userActivities.collectAsState()


    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    // Titulos de las paginas
    val tabs = listOf("Activites", "My activities")

    LaunchedEffect (authState.value){
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routes.Login.route)
            else -> Unit
        }

    }

    LaunchedEffect(accessToken) {
        accessToken?.let {
            activitiesViewModel.getAllActivities()
            activitiesViewModel.getUserActivities()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(90.dp))
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Blue,
                    contentColor = Color.White,
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
                0 -> AllActivitiesScreen(activities, activitiesViewModel)
                1 -> AllUserActivitiesScreen(userActivities!!, activitiesViewModel)
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun AllActivitiesScreen(activities : List<ActivityResponse>, activitiesViewModel: ActivitiesViewModel) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Todas las actividades", fontSize = 25.sp)

        if (activitiesViewModel.activities.value.isEmpty()) {
            // Muestra una barra circular mientras cargan actividades
            CircularProgressIndicator()
        } else {
            //Muestra todas las actividades
            LazyColumn {
                items(activities) { activity ->
                    ShowActivityWithSignUpButton(
                        activity,
                        Icons.Default.Star
                    ) {
                        coroutineScope.launch {
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
fun AllUserActivitiesScreen(userActivities : List<ActivityResponse>,activitiesViewModel: ActivitiesViewModel) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mis actividades", fontSize = 25.sp)
        if(activitiesViewModel.userActivities.value.isNullOrEmpty()){
            CircularProgressIndicator()
        }else{
            LazyColumn {
                items(userActivities) { activity ->
                    ShowActivityWithSignUpButton(
                        activity,
                        Icons.Default.Delete
                    ) {
                        coroutineScope.launch {
                            activitiesViewModel.deleteParticipation(activity.id)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowActivityWithSignUpButton(
    activity: ActivityResponse,
    buttonIcon: ImageVector,
    onClickAction: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
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
                color = MaterialTheme.colorScheme.primary,
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
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(imageVector = buttonIcon, contentDescription = "Button Icon", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Sign Up", color = Color.White)
            }
        }
    }

}




@Composable
fun ShowActivityWithDeleteButton(activity: ActivityResponse, activitiesViewModel: ActivitiesViewModel){
    val coroutineScope = rememberCoroutineScope()
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = misFormas.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Espaciado entre elementos

    ) {
        Row(Modifier.fillMaxSize(),)
        {
            Text(
                text = activity.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            Button(onClick =
            {
                coroutineScope.launch {
                    activitiesViewModel.deleteParticipation(activity.id.toString())
                }
            },
                shape = misFormas.small,
                modifier = Modifier.width(250.dp)

            ) {
                Text(
                    text = "Dejar actividad",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}