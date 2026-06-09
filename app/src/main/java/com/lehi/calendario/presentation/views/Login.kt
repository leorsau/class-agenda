package com.lehi.calendario.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lehi.calendario.R
import com.lehi.calendario.presentation.viewModels.LoginViewModel
import androidx.compose.runtime.getValue
import com.lehi.calendario.presentation.models.LoginUIEvent
import com.lehi.calendario.presentation.models.LoginUIState


@Composable
fun PantallaLogin() {

    val loginViewModel: LoginViewModel= viewModel()
    val uiState by loginViewModel.uiState.collectAsState()
    cuerpoPantallaLogin(
        uiState=uiState,
        onEvent=loginViewModel::onEvent
    )
}

@Composable
fun cuerpoPantallaLogin(
    uiState: LoginUIState,
    onEvent:(LoginUIEvent)-> Unit,
    modificadorCuerpoPantalla: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {
    Column(
        modifier = modificadorCuerpoPantalla
            .padding(vertical = 24.dp, horizontal = 12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "mi logo de la app",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 24.dp)
        )

        Text(
            text = "ClassAgenda",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 26.sp
        )

        Text(
            text = "Inicia sesión para gestionar tus estudios",
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange = {onEvent(LoginUIEvent.EmailCambiado(it))},
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
            value = uiState.contrasena,
            onValueChange = {onEvent(LoginUIEvent.ContrasenaCambiada(it))},
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

        if(uiState.mensajeError!=null){
            Text(
                text = uiState.mensajeError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(
            onClick = {
                onEvent(LoginUIEvent.EntrarClick)
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
                text = "Entrar",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 26.sp
            )
        }

        Text(
            text = "¿No tienes cuenta? Registrate",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            modifier = Modifier.clickable {

            }
        )
    }
}