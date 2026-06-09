package com.lehi.calendario.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun PantallaUserProfile(){
    CuerpoPantallaUserProfile()
}

@Composable
fun CuerpoPantallaUserProfile(
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
                    text = "Mi Perfil",
                    style=MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 85.dp)
                )

            }
        },//Fin de la top Bar
    ) {//inicio cuerpo Scaffold
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.usuario_perfil),
                contentDescription = "mi logo de perfil de usuario",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 24.dp)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = "Mi nombre",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 26.sp
            )
            Text(
                text = "ej: lehiortiz@hotmail.com",
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "ESTADÍSTICAS DEL CURSO",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tareas Completadas",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "12",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF00796B),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 1.dp, color = Color.White)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tareas Pendientes",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "12",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF00796B),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.7.dp, color = Color.White)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Eventos Próximos",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "12",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF00796B),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                }
            }//Fin de la primera card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x6B817777)
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "CUENTA",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )


                    Text(
                        text = "Editar datos personales",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(vertical = 4.dp)
                    )

                }
            }//fin de la segunda card


        }//fin de la colum del cuerpo del Scaffold
    }//fin del cuerpo del Scaffold
}