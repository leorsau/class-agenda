package com.lehi.calendario.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lehi.calendario.R

@Composable
fun PantallaRegister(
    onBackToLogin: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    cuerpoPantallaRegister(onBackToLogin, onRegisterSuccess)
}

@Composable
fun cuerpoPantallaRegister(
    onBackToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit,
    modificadorCuerpoPantalla: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {

    Column(
        modifier = modificadorCuerpoPantalla
            .padding(vertical = 32.dp, horizontal = 12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = { onBackToLogin() },
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Start)
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Icono de volver al login",
            )
        }

        Text(
            text = "Registro",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.Start),
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = "Crea tu cuenta de estudiante",
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            isError = false,
            label = {
                Text(
                    text = "Nombre",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            placeholder = {
                Text(
                    text = "ej pedro",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            isError = false,
            label = {
                Text(
                    text = "Correo electrónico",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            placeholder = {
                Text(
                    text = "estudiante@universidad.edu",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            isError = false,
            label = {
                Text(
                    text = "Contraseña",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            placeholder = {
                Text(
                    text = "***************",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            isError = false,
            label = {
                Text(
                    text = "Repite la contraseña",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            placeholder = {
                Text(
                    text = "***************",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onRegisterSuccess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        ) {
            Text(
                text = "Crear cuenta y entrar",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 26.sp
            )
        }
    }
}