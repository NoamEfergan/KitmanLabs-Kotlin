package com.example.kitmanlabs_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kitmanlabs_kotlin.Screens.LoginScreen.LoginScreen
import com.example.kitmanlabs_kotlin.Screens.MainScreen.MainScreen
import com.example.kitmanlabs_kotlin.Screens.MainScreen.MainScreenViewModel
import com.example.kitmanlabs_kotlin.ui.theme.KitmanLabsKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitmanLabsKotlinTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavRoot()
                }
            }
        }
    }
}

@Composable
fun NavRoot() {
    val mainScreenViewModel by remember { mutableStateOf(MainScreenViewModel()) }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen {
                navController.navigate("main/$it")
            }
        }
        composable("main/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        )
        { backStackEntry ->
            backStackEntry.arguments?.getString("name")?.let { MainScreen(it, mainScreenViewModel) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavRoot()
}