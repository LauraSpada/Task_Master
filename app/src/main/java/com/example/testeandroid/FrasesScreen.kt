package com.example.testeandroid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.graphics.Color

@Composable
fun FrasesScreen(
    taskList: MutableList<Task>, // lista mutável que irá receber os valores de task
    // etapa 1 -> cria a variável e diz oque ela é
    onOpenSettings: () -> Unit,  //lambida que não retorna valor
    onOpenConcluidas: () -> Unit //lambida que não retorna valor
) {
    var taskText by remember { mutableStateOf("") } // taskText armazena o for digitado

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Text(
            text = "Task Master", //obrigatório
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row( // parâmetros
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) { // funções do composable
            OutlinedTextField(
                value = taskText,
                onValueChange = { taskText = it },
                label = { Text("Digite sua task") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (taskText.isNotBlank()) {
                    taskList.add(Task(text = taskText))
                    taskText = "" // faz a task ficar vazia após outra task ser adicionada anteriormente
                }
            }, content = {  //pode ser subtituído por "{ Text("Adicionar") }"
                Text("Adicionar")
            })
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {  // lista preguiçosa -> lista para mostrar na tela
            itemsIndexed(taskList.filter { !it.isDone }) { index, task ->
                FraseCard(
                    task = task,
                    onDelete = {
                        val realIndex = taskList.indexOf(task)
                        if (realIndex != -1) taskList.removeAt(realIndex)
                    },
                    onToggleDone = {
                        val realIndex = taskList.indexOf(task)
                        if (realIndex != -1)
                            taskList[realIndex] = task.copy(isDone = !task.isDone)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onOpenSettings) {  // diz oque irá ser feito
            // etapa 2 -> oque irá ser feito
                Text("Configurações")
            }
            Button(onClick = onOpenConcluidas) {
                Text("Concluídas")
            }
        }
    }
}
