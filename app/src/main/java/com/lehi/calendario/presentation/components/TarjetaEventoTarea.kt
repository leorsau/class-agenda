package com.lehi.calendario.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lehi.calendario.R

@Composable
fun TarjetaEventoTarea(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE9EAEC)
        )
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFCDECEF)
                ) {
                    Text(
                       text = "Bases de datos",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style= MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Práctica de SQL",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icono_fecha),
                        contentDescription = "Fecha de entrega",
                        modifier = Modifier.size(16.dp),
                        tint = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Entrega: 2023-11-20",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.DarkGray
                    )
                }
            }
            Icon(
                painter = painterResource(id =R.drawable.icono_fecha),
                contentDescription = "Tipo tarea",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp),
                tint = Color.DarkGray
            )
        }
    }

}