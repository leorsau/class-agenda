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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lehi.calendario.R

@Composable
fun PantallaEventDetails(){
    CuerpoPantallaEventDetails()
}

@Composable
fun CuerpoPantallaEventDetails(modifier: Modifier= Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)){

    Scaffold(
        modifier=modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 20.dp),
        topBar = {
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(60.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.flecha),
                        contentDescription = "Atrás",
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = "Detalle del Evento",
                    style=MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {},
                    modifier = Modifier.size(60.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.borrar),
                        contentDescription = "Borrar",
                        modifier = Modifier.size(36.dp)
                    )
                }

                IconButton(onClick = {},
                    modifier = Modifier.size(60.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.borrar),
                        contentDescription = "Borrar",
                        modifier = Modifier.size(36.dp)
                    )
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
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
                ) {
                    Text(
                        text = "Guardar",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
                ) {
                    Text(
                        text = "Editar",
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
            Column(modifier = Modifier.padding(12.dp)) {

                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = "Título del evento",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text("estudiante@universidad.edu")
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

               Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                   horizontalArrangement = Arrangement.spacedBy(8.dp)){
                   Column(modifier = Modifier.weight(1f)) {
                       Text(
                           text = "Fecha inicio evento",
                           style = MaterialTheme.typography.bodyLarge
                       )

                       OutlinedTextField(
                           value = "",
                           onValueChange = {},
                           placeholder = {
                               Text("Seleccionar")
                           },
                           trailingIcon = {
                               Icon(
                                   painter = painterResource(id = R.drawable.icono_fecha),
                                   contentDescription = "Seleccionar hora",
                                   modifier = Modifier.size(20.dp)
                               )
                           },
                           modifier = Modifier.fillMaxWidth(),
                       )
                   }
                   Column(modifier = Modifier.weight(1f)) {

                           Text(
                               text = "Hora inicio evento",
                               style = MaterialTheme.typography.bodyLarge
                           )

                       OutlinedTextField(
                           value = "",
                           onValueChange = {},
                           placeholder = { Text("ej:10:00") },
                           trailingIcon = {
                               Icon(
                                   painter = painterResource(id = R.drawable.icono_hora),
                                   contentDescription = "Seleccionar hora",
                                   modifier = Modifier.size(20.dp)
                               )
                           },
                           modifier = Modifier.fillMaxWidth(),
                       )


                   }


               }

                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Fecha fin evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = {
                                Text("Seleccionar")
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.icono_fecha),
                                    contentDescription = "Seleccionar hora",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = "Hora fin evento",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("ej:12:00") },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.icono_hora),
                                    contentDescription = "Seleccionar hora",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )


                    }
                }

                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = "Descripción del evento",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text("ej: Aquí va la descripción de tu evento")
                        },
                        modifier = Modifier.fillMaxSize(),
                    )
                }

            }
        }

    }
}