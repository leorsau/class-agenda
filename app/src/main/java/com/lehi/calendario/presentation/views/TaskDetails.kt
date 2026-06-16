package com.lehi.calendario.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lehi.calendario.R
import com.lehi.calendario.domain.model.EstadoTarea
import com.lehi.calendario.domain.model.PrioridadTarea
import com.lehi.calendario.presentation.models.TaskDetailsUIEvent
import com.lehi.calendario.presentation.models.TaskDetailsUIState
import com.lehi.calendario.presentation.viewModels.TaskDetailsViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun PantallaTaskDetails(
    idTarea:Int?,
    onVolverClick:()-> Unit
) {
    val taskDetailsViewModel: TaskDetailsViewModel= viewModel()
    val uiState by taskDetailsViewModel.uiState.collectAsState()

    if(idTarea!=null && uiState.id!=idTarea){
        taskDetailsViewModel.cargarTarea(idTarea)
    }
    CuerpoPantallaTaskDetails(
        uiState=uiState,
        onEvent=taskDetailsViewModel::onEvent,
        onVolverClick=onVolverClick
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuerpoPantallaTaskDetails(
    uiState: TaskDetailsUIState,
    onEvent:(TaskDetailsUIEvent)-> Unit,
    onVolverClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {

    var mostrarDatePicker by remember{
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Detalle de la Tarea",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            onVolverClick()
                        },
                        modifier = Modifier.size(70.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.flecha),
                            contentDescription = "Atrás",
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Row() {
                        IconButton(
                            onClick = {
                                onEvent(TaskDetailsUIEvent.BorrarClick)
                                onVolverClick()
                            },
                            modifier = Modifier.size(70.dp),
                            enabled = uiState.id!=null
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.borrar),
                                contentDescription = "Borrar",
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        IconButton(
                            onClick = {
                                onEvent(TaskDetailsUIEvent.EditarClick)
                            },
                            modifier = Modifier.size(70.dp),
                            enabled = !uiState.modoEdicion
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.desbloquear),
                                contentDescription = "editarTarea",
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                }
            }

        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (uiState.titulo.isBlank()) {
                            onEvent(TaskDetailsUIEvent.GuardarClick)
                        } else {
                            onEvent(TaskDetailsUIEvent.GuardarClick)
                            onVolverClick()
                        }
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
                    enabled = uiState.modoEdicion
                ) {
                    Text(
                        text = "Guardar",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }

            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Título de la tarea",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.titulo,
                            onValueChange = {
                                onEvent(TaskDetailsUIEvent.TituloCambiado(it))
                            },
                            placeholder = {
                                Text("Escribe aquí el título de la tarea")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                            isError = uiState.mensajeError!=null,
                            supportingText = {
                                if(uiState.mensajeError!=null){
                                    Text(
                                        text = uiState.mensajeError,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        )
                    }


                    Column(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Fecha Tarea",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = formatearFechaTarea(uiState.fechaTarea),
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("Seleccionar")
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if(uiState.modoEdicion){
                                            mostrarDatePicker=true
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icono_fecha),
                                        contentDescription = "Seleccionar fecha",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }



                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Descripción de la tarea",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.descripcion,
                            onValueChange = {
                                onEvent(TaskDetailsUIEvent.DescripcionCambiada(it))
                            },
                            placeholder = {
                                Text("ej: Aquí va la descripción de tu tarea")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            enabled = uiState.modoEdicion
                        )
                    }

                }
            } //cierre de la primera card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding( 12.dp)) {
                    Text(
                        text = "Prioridad de tarea",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Row(
                        modifier= Modifier.fillMaxWidth().padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            RadioButton(
                                selected = uiState.prioridadTarea== PrioridadTarea.BAJA,
                                onClick = {
                                    onEvent(TaskDetailsUIEvent.PrioridadCambiada(PrioridadTarea.BAJA))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = uiState.modoEdicion
                            )
                            Text(
                                text = "BAJA",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = uiState.prioridadTarea== PrioridadTarea.MEDIA,
                                onClick = {
                                    onEvent(TaskDetailsUIEvent.PrioridadCambiada(PrioridadTarea.MEDIA))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = uiState.modoEdicion
                            )
                            Text(
                                text = "MEDIA",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = uiState.prioridadTarea== PrioridadTarea.ALTA,
                                onClick = {
                                    onEvent(TaskDetailsUIEvent.PrioridadCambiada(PrioridadTarea.ALTA))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = uiState.modoEdicion
                            )
                            Text(
                                text = "ALTA",
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }//cierre de la segund card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {

                Column(modifier = Modifier.padding( 12.dp)) {
                    Text(
                        text = "Estado de la tarea",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Row(
                        modifier= Modifier.fillMaxWidth().padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            RadioButton(
                                selected = uiState.estadoTarea== EstadoTarea.PENDIENTE,
                                onClick = {
                                    onEvent(TaskDetailsUIEvent.EstadoCambiado(EstadoTarea.PENDIENTE))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = uiState.modoEdicion
                            )
                            Text(
                                text = "PENDIENTE",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = uiState.estadoTarea== EstadoTarea.FINALIZADA,
                                onClick = {
                                    onEvent(TaskDetailsUIEvent.EstadoCambiado(EstadoTarea.FINALIZADA))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = uiState.modoEdicion
                            )
                            Text(
                                text = "FINALIZADA",
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }//cierre tercera card


        }//cierre de la columna



    }//cierre del Scaffold
    if (mostrarDatePicker) {
        val fechaInicialMillis = uiState.fechaTarea
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = fechaInicialMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                mostrarDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val fechaSeleccionadaMillis = datePickerState.selectedDateMillis

                        if (fechaSeleccionadaMillis != null) {
                            val fechaSeleccionada = Instant
                                .ofEpochMilli(fechaSeleccionadaMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()

                            onEvent(TaskDetailsUIEvent.FechaCambiada(fechaSeleccionada))
                        }

                        mostrarDatePicker = false
                    }
                ) {
                    Text(
                        text = "Aceptar",
                        color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDatePicker = false
                    }
                ) {
                    Text(text = "Cancelar",
                        color = MaterialTheme.colorScheme.primary)
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

}//cierre de la función

fun formatearFechaTarea(fecha: LocalDate): String {
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return fecha.format(formato)
}
