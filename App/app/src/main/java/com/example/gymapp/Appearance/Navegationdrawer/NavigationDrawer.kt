package com.example.gymapp.Appearance.Navegationdrawer

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymapp.Appearance.Data.Routes
import com.example.gymapp.Appearance.Principal
import com.example.gymapp.Appearance.Views.AboutUsContent
import com.example.gymapp.Appearance.Views.Api.BodyPartsContent
import com.example.gymapp.Appearance.Views.Api.ExercisesContent
import com.example.gymapp.Appearance.Views.Authentication.Login
import com.example.gymapp.Appearance.Views.Authentication.Register
import com.example.gymapp.Appearance.Views.SettingsContent
import com.example.gymapp.GymApi.ViewModels.AuthViewModel
import com.example.gymapp.GymApi.ViewModels.BodyPartsViewModel
import com.example.gymapp.GymApi.ViewModels.ExercisesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(bodyPartsViewModel: BodyPartsViewModel,exercisesViewModel: ExercisesViewModel,authViewModel: AuthViewModel){
    val navController = rememberNavController()

    val showDialog = remember { mutableStateOf(false) }


    ///List of Navigation Items that will be clicked
    val items = listOf(
        NavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Routes.Principal.route
        ),
        NavigationItems(
            title = "Info",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = Routes.AboutUs.route
        ),
        NavigationItems(
            title = "Edit",
            selectedIcon = Icons.Filled.Edit,
            unselectedIcon = Icons.Outlined.Edit,
            route = Routes.BodyPartsScreen.route
        ),
        NavigationItems(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            route = Routes.Settings.route
        ),
        NavigationItems(
            title = "Cuenta",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = Routes.Login.route
        )
    )

    /*Button(
        onClick = { showDialog.value = true }, //no se puede llamar a la función directamente desde el onclick
        shape = misFormas.small,
        modifier = Modifier.width(250.dp)
    ) {
        Text(
            text = context.getString(R.string.exit_button_title),
            style = MaterialTheme.typography.headlineSmall,
        )
    }

    val activity = (LocalContext.current as? Activity)
    val imageBitmap = ImageBitmap.imageResource(R.drawable.icon)
    // Llama al AlertDialog genérico y lo muestra
    if (showDialog.value) {
        AlertDialog(
            title =  context.getString(R.string.exit_title),
            description =  context.getString(R.string.exit_description),
            icon = imageBitmap,
            confirmText =  context.getString(R.string.exit_confirm),
            dismissText = context.getString(R.string.exit_cancel),
            confirm = {
                activity?.finish()
                showDialog.value = false
            },
            dismiss = {
                showDialog.value = false
            })*/

    //Remember Clicked item state
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    //Remember the State of the drawer. Closed/ Opened
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp)) //space (margin) from top
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            if(item.route != null) {
                                navController.navigate(item.route)
                            }

                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {  // Show Badge
                            //item.badgeCount?.let {
                              //  Text(text = item.badgeCount.toString())
                            //}
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding) //padding between items
                    )
                }

            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = { //TopBar to show title
                TopAppBar(
                    title = {
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(  //Show Menu Icon on TopBar
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) {
            NavHost(navController = navController, startDestination = Routes.Principal.route) {
                composable(Routes.Principal.route) { selectedItemIndex = 0
                    Principal(navController, authViewModel) }
                composable(Routes.Settings.route) { selectedItemIndex = 3
                    SettingsContent(navController, authViewModel) }
                composable(Routes.AboutUs.route) { selectedItemIndex = 1
                    AboutUsContent(navController) }
                composable(Routes.BodyPartsScreen.route) { selectedItemIndex = 2
                    BodyPartsContent(navController, bodyPartsViewModel) }
                composable(Routes.ExercisesScreen.route) { selectedItemIndex = 2
                    ExercisesContent(navController, exercisesViewModel) }
                composable(Routes.Login.route) { selectedItemIndex = 4
                    Login(navController, authViewModel) }
                composable(Routes.Register.route) { selectedItemIndex = 4
                    Register(navController, authViewModel) }
            }
        }
    }


}