package com.lehi.calendario.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun PopupBotonHome(
    onCrearTareaClick: () -> Unit,
    onCrearEventoClick: () -> Unit,
    onCerrarClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCerrarClick,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Crear nuevo", style = MaterialTheme.typography.titleMedium)

                IconButton(onClick = onCerrarClick) {
                    Text("X")
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onCrearTareaClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear tarea")
                }

                Button(
                    onClick = onCrearEventoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear evento")
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}