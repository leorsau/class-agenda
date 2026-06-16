package com.lehi.calendario.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lehi.calendario.R
import com.lehi.calendario.presentation.models.EventDetailsUIEvent
import com.lehi.calendario.presentation.models.EventDetailsUIState
import com.lehi.calendario.presentation.viewModels.EventDetailsViewModel
import java.time.LocalTime
import androidx.compose.runtime.setValue
import java.time.Instant
import java.time.ZoneId
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PantallaEventDetails(
    idEvento:Int?,
    onVolverClick:()-> Unit
){
    val eventDetailsViewModel: EventDetailsViewModel= viewModel()
    val uiState by eventDetailsViewModel.uiState.collectAsState()

    if(idEvento!=null && uiState.id!=idEvento){
        eventDetailsViewModel.cargarEvento(idEvento)
    }
    CuerpoPantallaEventDetails(
        uiState=uiState,
        onEvent=eventDetailsViewModel::onEvent,
        onVolverClick=onVolverClick,
        horasDisponibles=eventDetailsViewModel.horasDisponibles
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuerpoPantallaEventDetails(
    uiState: EventDetailsUIState,
    onEvent:(EventDetailsUIEvent)-> Unit,
    onVolverClick: () -> Unit,
    horasDisponibles: List<LocalTime>,
    modifier: Modifier= Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)){

    var fechaSeleccionada by remember{
        mutableStateOf("")
    }
    var horaSeleccionada by remember {
        mutableStateOf("")
    }
    val fechaInicioCompleta = java.time.LocalDateTime.of(
        uiState.fechaInicioEvento,
        uiState.horaInicioEvento
    )

    val fechaFinCompleta = java.time.LocalDateTime.of(
        uiState.fechaFinEvento,
        uiState.horaFinEvento
    )

    Scaffold(
        modifier=modifier.padding(horizontal = 20.dp)
            .padding(bottom = 20.dp),
        topBar = {
            Column(
                modifier= Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "Detalle del Evento",
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
                                onEvent(EventDetailsUIEvent.BorrarClick)
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
                                onEvent(EventDetailsUIEvent.EditarClick)
                            },
                            modifier = Modifier.size(70.dp),
                            enabled = !uiState.modoEdicion
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.desbloquear),
                                contentDescription = "editarEvento",
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                }
            }

        },

        bottomBar = {
            Row(
                modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if(uiState.titulo.isBlank() || fechaFinCompleta.isBefore(fechaInicioCompleta)){
                            onEvent(EventDetailsUIEvent.GuardarClick)
                        }else{
                            onEvent(EventDetailsUIEvent.GuardarClick)
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
    ) {innerPadding ->

            Card(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Título del evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.titulo,
                            onValueChange = {
                                onEvent(EventDetailsUIEvent.TituloCambiado(it))
                            },
                            placeholder = {
                                Text("Escribe aquí el título del evento")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                            isError = uiState.mensajeErrorTitulo!=null,
                            supportingText = {
                                if(uiState.mensajeErrorTitulo!=null){
                                    Text(
                                        text = uiState.mensajeErrorTitulo,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        )
                    }

                    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                        Text(
                            text = "Fecha inicio evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = formatearFecha(uiState.fechaInicioEvento),
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("Seleccionar")
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if(uiState.modoEdicion){
                                            fechaSeleccionada="inicio"
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icono_fecha),
                                        contentDescription = "Seleccionar fecha inicio",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                        )
                    }//fecha inicio evento fin columna

                    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {

                        Text(
                            text = "Hora inicio evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.horaInicioEvento.toString(),
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("ej:10:00") },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if(uiState.modoEdicion){
                                            horaSeleccionada="inicio"
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icono_hora),
                                        contentDescription = "Seleccionar hora inicio",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                        )
                        DropdownMenu(
                            expanded = horaSeleccionada=="inicio",
                            onDismissRequest = {
                                horaSeleccionada=""
                            },
                            modifier= Modifier
                                .fillMaxWidth()
                                .heightIn(max = 250.dp)
                        ) {
                            horasDisponibles.forEach {
                                hora->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = hora.toString(),
                                            color = Color.Black)
                                    },
                                    onClick = {
                                        onEvent(EventDetailsUIEvent.HoraInicioCambiada(hora))
                                        horaSeleccionada=""
                                    }
                                )
                            }
                        }
                    }// hora inicio evento fin columna


                    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                        Text(
                            text = "Fecha fin evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = formatearFecha(uiState.fechaFinEvento),
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("Seleccionar")
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if(uiState.modoEdicion){
                                            fechaSeleccionada="fin"
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icono_fecha),
                                        contentDescription = "Seleccionar fecha fin",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                        )
                    }//fecha fin evento fin columna

                    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {

                        Text(
                            text = "Hora fin evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.horaFinEvento.toString(),
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("ej:12:00") },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        if (uiState.modoEdicion) {
                                            horaSeleccionada = "fin"
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icono_hora),
                                        contentDescription = "Seleccionar hora fin",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = uiState.modoEdicion,
                            isError = uiState.mensajeErrorFechaFin!=null,
                            supportingText = {
                                if(uiState.mensajeErrorFechaFin!=null){
                                    Text(
                                        text = uiState.mensajeErrorFechaFin,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        )
                        DropdownMenu(
                            expanded = horaSeleccionada == "fin",
                            onDismissRequest = {
                                horaSeleccionada = ""
                            },
                            modifier= Modifier
                                .fillMaxWidth()
                                .heightIn(max = 250.dp)
                        ) {
                            horasDisponibles.forEach { hora ->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = hora.toString(),
                                            color = Color.Black)
                                    },
                                    onClick = {
                                        onEvent(EventDetailsUIEvent.HoraFinCambiada(hora))
                                        horaSeleccionada = ""
                                    }
                                )
                            }
                        }
                    }//hora fin evento fin columna


                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Descripción del evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.descripcion,
                            onValueChange = {
                                onEvent(EventDetailsUIEvent.DescripcionCambiada(it))
                            },
                            placeholder = {
                                Text("ej: Aquí va la descripción de tu evento")
                            },
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            enabled = uiState.modoEdicion
                        )
                    }

                }
            }// fin de la card del cuerpo


    }//fin del cuerpo scafold

    if (fechaSeleccionada != "") {

        val fechaInicialMillis = if (fechaSeleccionada == "inicio") {
            uiState.fechaInicioEvento
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        } else {
            uiState.fechaFinEvento
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        }

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = fechaInicialMillis
        )

        DatePickerDialog(
            onDismissRequest = {
                fechaSeleccionada = ""
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val fechaSeleccionadaMillis = datePickerState.selectedDateMillis

                        if (fechaSeleccionadaMillis != null) {
                            val nuevaFecha = Instant
                                .ofEpochMilli(fechaSeleccionadaMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()

                            if (fechaSeleccionada == "inicio") {
                                onEvent(EventDetailsUIEvent.FechaInicioCambiada(nuevaFecha))
                            } else if (fechaSeleccionada == "fin") {
                                onEvent(EventDetailsUIEvent.FechaFinCambiada(nuevaFecha))
                            }
                        }

                        fechaSeleccionada = ""
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
                        fechaSeleccionada = ""
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
}

fun formatearFecha(fecha: LocalDate): String {
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return fecha.format(formato)
}