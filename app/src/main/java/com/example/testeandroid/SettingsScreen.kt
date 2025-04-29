package com.example.testeandroid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun SettingsScreen(
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Configurações",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Tema escuro")
            Switch(
                checked = isDark,
                onCheckedChange = { onToggleTheme() },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
