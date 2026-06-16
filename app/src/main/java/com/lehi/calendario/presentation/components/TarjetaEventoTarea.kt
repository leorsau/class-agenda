package com.lehi.calendario.presentation.components

import androidx.compose.foundation.clickable
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
import com.lehi.calendario.presentation.models.HomeItem
import com.lehi.calendario.presentation.models.HomeUIEvent
import com.lehi.calendario.presentation.models.TipoItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TarjetaEventoTarea(
    item: HomeItem,
    onClick: ()-> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SurfaceTipoItem(item.tipo)

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = item.titulo,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )


            if (!item.descripcion.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            if (item.tipo == TipoItem.TAREA) {
                FilaFechaTarea(item)
            } else {
                FilaFechaEvento(item)
            }
        }
    }
}

@Composable
fun SurfaceTipoItem(tipo: TipoItem) {

    val colorFondo = when (tipo) {
        TipoItem.TAREA -> Color(0xD2034750)
        TipoItem.EVENTO -> Color(0xFFFFE082)
    }

    val colorTexto = when (tipo) {
        TipoItem.TAREA -> Color.White
        TipoItem.EVENTO -> Color.Black
    }

    Surface(
        color = colorFondo,
        shape = RoundedCornerShape(8.dp)
    ) {


        Text(
            text = if (tipo == TipoItem.TAREA) "Tarea" else "Evento",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelLarge,
            color = colorTexto
        )
    }
}


@Composable
fun FilaFechaTarea(item: HomeItem) {
    Row {
        Text(
            text = "Fecha: ${formatearFechaTarjeta(item.fechaInicio)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FilaFechaEvento(item: HomeItem) {
    Column {

        Row {
            Text(
                text = "Inicio: ${formatearFechaTarjeta(item.fechaInicio)} ${item.horaInicio}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Text(
                text = "Fin: ${formatearFechaTarjeta(item.fechaFin)} ${item.horaFin}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun formatearFechaTarjeta(fechaTexto: String?): String {
    if (fechaTexto == null) {
        return ""
    }

    val fecha = LocalDate.parse(fechaTexto)
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    return fecha.format(formato)
}
