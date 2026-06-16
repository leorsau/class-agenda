package com.lehi.calendario.presentation.views

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lehi.calendario.R
import com.lehi.calendario.presentation.components.PopupBotonHome
import com.lehi.calendario.presentation.components.TarjetaEventoTarea
import com.lehi.calendario.presentation.models.HomeUIEvent
import com.lehi.calendario.presentation.models.HomeUIState
import com.lehi.calendario.presentation.viewModels.HomeViewModel
import androidx.compose.foundation.lazy.items
import com.lehi.calendario.presentation.models.FiltroHome
import com.lehi.calendario.presentation.models.TipoItem


@Composable
fun PantallaHome(
    onCrearTareaClick: () -> Unit,
    onCrearEventoClick: () -> Unit,
    onAbrirTareaClick: (Int) -> Unit,
    onAbrirEventoClick: (Int) -> Unit,
    onPerfilClick: () -> Unit
) {
    val homeViewModel: HomeViewModel= viewModel()
    val uiState by homeViewModel.uiState.collectAsState()
    cuerpoPantallaHome(
        uiState=uiState,
        onEvent=homeViewModel::onEvent,
        onCrearTareaClick = onCrearTareaClick,
        onCrearEventoClick = onCrearEventoClick,
        onAbrirTareaClick = onAbrirTareaClick,
        onAbrirEventoClick = onAbrirEventoClick,
        onPerfilClick = onPerfilClick
    )
}

@Composable
fun cuerpoPantallaHome(
    uiState: HomeUIState,
    onEvent:(HomeUIEvent)-> Unit,
    onCrearTareaClick: () -> Unit,
    onCrearEventoClick: () -> Unit,
    onAbrirTareaClick: (Int) -> Unit,
    onAbrirEventoClick: (Int) -> Unit,
    onPerfilClick: () -> Unit,
    modificadorCuerpoPantalla: Modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
    Column(modifier = modificadorCuerpoPantalla) {
        Scaffold(
            modifier = modificadorCuerpoPantalla.padding(16.dp),
            containerColor = Color.Transparent,
            content = { innerPadding ->
                CuerpoScaffold(
                    uiState=uiState,
                    onEvent=onEvent,
                    onAbrirTareaClick,
                    onAbrirEventoClick,
                    modifier = Modifier.padding(innerPadding)) },
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(70.dp)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Inicio",
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            onPerfilClick()
                        },
                        modifier = Modifier
                            .size(42.dp)
                            .padding(end = 6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Icono de acceso a perfil usuario",
                        )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                Button(
                    onClick = {
                        onEvent(HomeUIEvent.AbrirPopupCrearClick)
                    },
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    ) {
                    Text(
                        text = "+",
                        fontSize = 28.sp,
                    )
                }
            }
        )
    }
    if(uiState.mostrarPopupCrear) {
        PopupBotonHome(
            onCrearTareaClick = {
                onEvent(HomeUIEvent.CrearTareaClick)
                onCrearTareaClick()
            },
            onCrearEventoClick = {
                onEvent(HomeUIEvent.CrearEventoClick)
                onCrearEventoClick()
            },
            onCerrarClick = {
                onEvent(HomeUIEvent.CerrarPopupCrearClick)
            }
        )
    }
}


@Composable
fun CuerpoScaffold(
    uiState: HomeUIState,
    onEvent: (HomeUIEvent) -> Unit,
    onAbrirTareaClick: (Int) -> Unit,
    onAbrirEventoClick: (Int) -> Unit,
    modifier:Modifier= Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(bottom = 16.dp)
    ) {

        Text(
            text = "TU AGENDA",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier=Modifier.padding(top = 12.dp),
            text = "Próximos eventos y tareas",
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                onClick = {
                    onEvent(HomeUIEvent.FiltroTodosClick)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(uiState.filtroSeleccionado== FiltroHome.TODOS){
                        MaterialTheme.colorScheme.primary
                    }else{
                        Color.White
                    }
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation=0.dp
                )
            ) {
                Text(
                    text = "Todos",
                    fontSize = 18.sp,
                    color=if(uiState.filtroSeleccionado== FiltroHome.TODOS){
                        Color.White
                    }else{
                        Color.Black
                    }
                )
            }

            Button(
                onClick = {
                    onEvent(HomeUIEvent.FiltroTareasClick)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(uiState.filtroSeleccionado== FiltroHome.TAREAS){
                        MaterialTheme.colorScheme.primary
                    }else{
                      Color.White
                    }
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation=0.dp
                )
            ) {
                Text(
                    text = "Tareas",
                    fontSize = 18.sp,
                    color=if(uiState.filtroSeleccionado== FiltroHome.TAREAS){
                        Color.White
                    }else{
                        Color.Black
                    }
                )
            }

            Button(
                onClick = {
                    onEvent(HomeUIEvent.FiltroEventosClick)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(uiState.filtroSeleccionado== FiltroHome.EVENTOS){
                        MaterialTheme.colorScheme.primary
                    }else{
                        Color.White
                    }
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation=0.dp
                )
            ) {
                Text(
                    text = "Eventos",
                    fontSize = 18.sp,
                    color=if(uiState.filtroSeleccionado== FiltroHome.EVENTOS){
                        Color.White
                    }else{
                        Color.Black
                    }
                )
            }
        }
        columnaDeTarjetas(
            uiState=uiState,
            onAbrirTareaClick,
            onAbrirEventoClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }


}

@Composable
fun columnaDeTarjetas(
    uiState: HomeUIState,
    onAbrirTareaClick: (Int) -> Unit,
    onAbrirEventoClick: (Int) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
){
    LazyColumn(
        modifier=modifier
    ) {
        if (uiState.items.isEmpty()) {
            item {
                Text(
                    text = "No hay tareas ni eventos próximos",
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            items(uiState.items) { item ->
                TarjetaEventoTarea(
                    item = item,
                    onClick = {
                        if(item.tipo== TipoItem.TAREA){
                            onAbrirTareaClick(item.id)
                        }else if(item.tipo== TipoItem.EVENTO){
                            onAbrirEventoClick(item.id)
                        }
                    }
                )
            }
        }
    }
}





