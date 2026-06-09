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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lehi.calendario.R
import com.lehi.calendario.presentation.components.TarjetaEventoTarea


@Composable
fun PantallaHome() {
    cuerpoPantallaHome()
}

@Composable
fun cuerpoPantallaHome(modificadorCuerpoPantalla: Modifier= Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)) {
    Column(modifier = modificadorCuerpoPantalla) {
        Scaffold(
            modifier = modificadorCuerpoPantalla.padding(16.dp),
            containerColor = Color.Transparent,
            content = { innerPadding ->CuerpoScaffold( modifier = Modifier.padding(innerPadding)) },
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth()
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
                        onClick = {},
                        modifier = Modifier.size(42.dp)
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
                Button(onClick = {},
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    ) {
                    Text(
                        text = "+",
                        fontSize = 32.sp,
                    )
                }
            }
        )
    }
}


@Composable
fun CuerpoScaffold(modifier:Modifier= Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
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

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
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
                    color=Color.Black
                )
            }

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
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
                    color=Color.Black
                )
            }

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
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
                    color=Color.Black
                )
            }
        }
        columnaDeTarjetas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }


}

@Composable
fun columnaDeTarjetas(
    modifier: Modifier = Modifier.fillMaxSize()
){
    LazyColumn(

    ) {
        item { TarjetaEventoTarea() }
    }
}





