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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lehi.calendario.R
import com.lehi.calendario.presentation.models.UserEditProfileUIEvent
import com.lehi.calendario.presentation.models.UserEditProfileUIState
import com.lehi.calendario.presentation.viewModels.UserEditProfileViewModel

@Composable
fun PantallaUserEditProfile(
    onVolverClick:()-> Unit,
    onPerfilActualizado:()-> Unit
){
    val userEditProfileViewModel: UserEditProfileViewModel= viewModel()
    val uiState by userEditProfileViewModel.uiState.collectAsState()

    if(uiState.perfilActualizado){
        onPerfilActualizado()
    }

    CuerpoPantallaUserEditProfile(
        uiState=uiState,
        onEvent=userEditProfileViewModel::onEvent,
        onVolverClick=onVolverClick)
}

@Composable
fun CuerpoPantallaUserEditProfile(
    uiState: UserEditProfileUIState,
    onEvent: (UserEditProfileUIEvent) -> Unit,
    onVolverClick: () -> Unit,
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
                    onClick = {
                        onVolverClick()
                    },
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
                    onClick = { onEvent(UserEditProfileUIEvent.GuardarClick)},
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                            value = uiState.nombreActual,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        Text(
                            text = "Nombre Nuevo",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.nuevoNombre,
                            onValueChange = {
                                onEvent(UserEditProfileUIEvent.NuevoNombreCambiado(it))
                            },
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
                            value = uiState.contrasenaActual,
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
                            text = "Nueva Contraseña",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        OutlinedTextField(
                            value = uiState.nuevaContrasena,
                            onValueChange = {
                                onEvent(UserEditProfileUIEvent.NuevaContrasenaCambiada(it))
                            },
                            readOnly = false,
                            placeholder = {
                                Text("ej: nuevapassword123")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }
                }
            }//fin de la segunda Card
            if (uiState.mensajeError != null) {
                Text(
                    text = uiState.mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

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
                            text = "Confirmar Nueva Contraseña",
                            style = MaterialTheme.typography.bodyLarge,
                        )

                        OutlinedTextField(
                            value = uiState.repetirNuevaContrasena,
                            onValueChange = {
                                onEvent(UserEditProfileUIEvent.RepetirNuevaContrasenaCambiada(it))
                            },
                            readOnly = false,
                            placeholder = {
                                Text("ej: nuevapassword123")
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                }
            }//fin de la tercera Card



            if (uiState.perfilActualizado) {
                Text(
                    text = "Perfil actualizado correctamente",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }//fin de la colum del cuerpo del Scaffold

    }//fin del cuerpo del Scaffold
}