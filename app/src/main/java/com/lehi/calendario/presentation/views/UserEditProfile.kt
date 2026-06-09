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
fun PantallaUserEditProfile(){
    CuerpoPantallaUserEditProfile()
}

@Composable
fun CuerpoPantallaUserEditProfile(
    modificadorCuerpoPantalla: Modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
{
    Scaffold(
        containerColor = Color.Transparent,
        modifier = modificadorCuerpoPantalla
            .padding(horizontal = 16.dp),
        topBar = {
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
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
                    text = "Volver",
                    style=MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 85.dp)
                )

            }
        },//Fin de la top Bar

        bottomBar = {
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
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

            }

        }//fin de la botton bar

    ) {//inicio cuerpo Scaffold
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Column(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                        Text(
                            text = "Nombre Actual",
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("lehi ortiz")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Nombre Nuevo",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = false,
                            placeholder = {
                                Text("ej: lehisito")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }
                }
            }//fin de la primera card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Column(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                        Text(
                            text = "Contraseña Actual",
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("*************")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Repita Contraseña",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = false,
                            placeholder = {
                                Text("*************")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }
                }
            }//fin de la segunda Card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Column(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                        Text(
                            text = "Nueva Contraseña",
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text("ej: nuevapassword12345")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                }
            }//fin de la tercera Card

        }//fin de la colum del cuerpo del Scaffold
    }//fin del cuerpo del Scaffold
}