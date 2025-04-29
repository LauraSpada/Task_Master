package com.example.testeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.testeandroid.ConcluidasScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            val taskList = remember { mutableStateListOf<Task>() } // Compartilhada entre as telas

            MaterialTheme(
                colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
// azul é o parâmetro
                    NavHost(navController = navController, startDestination = "home") { // azul recebe o branco que é a variável que delcarei acima
                        composable("home") { // começa no home, que no caso é a frasesScreen.kt
                            FrasesScreen(
                                taskList = taskList, // azul é o prarâmetro e branco é oque foi declarado
                                onOpenSettings = { navController.navigate("settings") },
                                onOpenConcluidas = { navController.navigate("concluidas") }
                                // etapa 3 -> chamar a função que foi criada e dizer oque são so parâmetros
                                // declarando oque a lambida é
                            )
                        }
                        composable("settings") {
                            SettingsScreen(
                                isDark = isDarkTheme,
                                onToggleTheme = { isDarkTheme = !isDarkTheme },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable("concluidas") {
                            ConcluidasScreen(
                                taskList = taskList,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}