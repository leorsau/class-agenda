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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
fun PantallaTaskDetails() {
    CuerpoPantallaTaskDetails()
}

@Composable
fun CuerpoPantallaTaskDetails(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 24.dp)
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.flecha),
                        contentDescription = "Atrás",
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = "Detalle de la Tarea",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(70.dp)
                ) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 20.dp),
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
                            value = "",
                            onValueChange = {},
                            placeholder = {
                                Text("Ecribe aquí el titulo de la tarea")
                            },
                            modifier = Modifier.fillMaxWidth(),
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



                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Descripción de la tarea",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = {
                                Text("ej: Aquí va la descripción de tu tarea")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
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
                                selected = false,
                                onClick = {},
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(
                                text = "BAJA",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = false,
                                onClick = {},
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(
                                text = "MEDIA",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = false,
                                onClick = {},
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                )
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
                                selected = true,
                                onClick = {},
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                ),
                                enabled = false
                            )
                            Text(
                                text = "PENDIENTE",
                                color = Color.DarkGray
                            )
                        }

                        Column() {
                            RadioButton(
                                selected = false,
                                onClick = {},
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.DarkGray
                                )
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

}//cierre de la función
