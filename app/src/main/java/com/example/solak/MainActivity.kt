import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.solak.ui.theme.SolakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolakTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            cutoutShape = CircleShape,
                            backgroundColor = MaterialTheme.colors.surface
                        ) {
                            val items = listOf(
                                Screen.Home,
                                Screen.Events,
                                Screen.Photos,
                                Screen.Chat,
                                Screen.Settings
                            )
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                                    label = { Text(screen.label) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Home.route, Modifier.fillMaxSize()) {
                        composable(Screen.Home.route) { HomeScreen() }
                        composable(Screen.Events.route) { EventsScreen() }
                        composable(Screen.Photos.route) { PhotosScreen() }
                        composable(Screen.Chat.route) { ChatScreen() }
                        composable(Screen.Settings.route) { SettingsScreen() }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Events : Screen("events", "Events", Icons.Filled.Event)
    object Photos : Screen("photos", "Photos", Icons.Filled.Photo)
    object Chat : Screen("chat", "Chat", Icons.Filled.Chat)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
}

@Composable
fun HomeScreen() {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Text("Home Screen")
    }
}

@Composable
fun EventsScreen() {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Text("Events Screen")
    }
}

@Composable
fun PhotosScreen() {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Text("Photos Screen")
    }
}

@Composable
fun ChatScreen() {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Text("Chat Screen")
    }
}

@Composable
fun SettingsScreen() {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Text("Settings Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SolakTheme {
        BottomNavigationBar()
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Events,
        Screen.Photos,
        Screen.Chat,
        Screen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

