package com.example.gymapp.Appearance.Views.Activities

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Generics.CreateCard
import com.example.gymapp.Appearance.Themes.misFormas
import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.ViewModels.Activities.ActivitiesViewModel
import com.example.gymapp.GymApi.ViewModels.Auth.AuthState
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel
import com.example.gymapp.R
import kotlinx.coroutines.launch

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

    Scaffold( topBar = {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier,
            containerColor = Color.Blue,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.apply {
                    HorizontalDivider(Modifier
                        .height(2.dp)
                        .padding(horizontal = 16.dp)
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            divider ={}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        // Para sincronizar el tabRow con el horizontal pager
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {
                        when (index) { // Iconos de cada pagina
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = null)
                        }
                    }
                )
            }
        }
    }) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            when (page) { // Poner las paginas necesarias
                0 -> AllActivitiesScreen(activities,activitiesViewModel)
                1 -> AllUserActivitiesScreen(userActivities!!,activitiesViewModel)
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AllActivitiesScreen(activities : List<ActivityResponse>, activitiesViewModel: ActivitiesViewModel) {
    Column(
        Modifier.fillMaxSize(),
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
                    showActivityWithSignUpButton(activity)
                }
            }
        }

    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AllUserActivitiesScreen(userActivities : List<ActivityResponse>,activitiesViewModel: ActivitiesViewModel) {
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
                    showActivityWithDeleteButton(activity,activitiesViewModel)
                }
            }
        }
    }
}


@Composable
fun showActivityWithSignUpButton(activity: ActivityResponse){
    Text(activity.title)
}

@Composable
fun showActivityWithDeleteButton(activity: ActivityResponse, activitiesViewModel: ActivitiesViewModel){
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
                    activitiesViewModel.deleteParticipation(activity.id)
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