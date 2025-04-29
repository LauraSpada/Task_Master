package com.example.testeandroid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.itemsIndexed

@Composable
fun ConcluidasScreen(
    taskList: MutableList<Task>,
    onBack: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            text = "Concluídas",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn {
            itemsIndexed(taskList.filter { it.isDone }) { index, task ->
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

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Voltar")
        }
    }
}