package com.lehi.calendario.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lehi.calendario.presentation.views.PantallaEventDetails
import com.lehi.calendario.presentation.views.PantallaHome
import com.lehi.calendario.presentation.views.PantallaLogin
import com.lehi.calendario.presentation.views.PantallaRegister
import com.lehi.calendario.presentation.views.PantallaTaskDetails
import com.lehi.calendario.presentation.views.PantallaUserEditProfile
import com.lehi.calendario.presentation.views.PantallaUserProfile

@Composable
fun Navegacion() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        composable(Rutas.LOGIN) {
            PantallaLogin(
                onLoginCorrecto = {
                    navController.navigate(Rutas.HOME){
                        popUpTo(Rutas.LOGIN){
                            inclusive=true
                        }
                    }
                },
                onCrearCuentaClick = {
                    navController.navigate(Rutas.REGISTER)
                }
            )
        }

        composable(Rutas.REGISTER) {
            PantallaRegister(
                onRegistroCorrecto = {
                    navController.navigate(Rutas.HOME){
                        popUpTo(Rutas.LOGIN){
                            inclusive=true
                        }
                    }
                }
            )
        }

        composable(Rutas.HOME) {
            PantallaHome(
                onCrearTareaClick = {
                    navController.navigate(Rutas.TASK_DETAILS)
                },
                onCrearEventoClick = {
                    navController.navigate(Rutas.EVENT_DETAILS)
                },
                onAbrirTareaClick = { idTarea ->
                    navController.navigate(Rutas.taskDetailsConId(idTarea))
                },
                onAbrirEventoClick = { idEvento ->
                    navController.navigate(Rutas.eventDetailsConId(idEvento))
                },
                onPerfilClick = {
                    navController.navigate(Rutas.USER_PROFILE)
                }
            )

        }

        composable(Rutas.TASK_DETAILS) {
            PantallaTaskDetails(
                idTarea = null,
                onVolverClick = {
                    navController.navigate(Rutas.HOME)
                }
            )
        }

        composable(
            route = "${Rutas.TASK_DETAILS}/{idTarea}",
            arguments = listOf(
                navArgument("idTarea") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val idTarea = backStackEntry.arguments?.getInt("idTarea")

            PantallaTaskDetails(
                idTarea=idTarea,
                onVolverClick = {
                    navController.navigate(Rutas.HOME)
                }
            )

        }

        composable(Rutas.EVENT_DETAILS) {
            PantallaEventDetails(
                idEvento = null,
                onVolverClick = {
                    navController.navigate(Rutas.HOME)
                }
            )
        }

        composable(
            route = "${Rutas.EVENT_DETAILS}/{idEvento}",
            arguments = listOf(
                navArgument("idEvento") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val idEvento = backStackEntry.arguments?.getInt("idEvento")

            PantallaEventDetails(
                idEvento=idEvento,
                onVolverClick = {
                    navController.navigate(Rutas.HOME)
                }
            )
        }

        composable(Rutas.USER_PROFILE) {
            PantallaUserProfile(
                onVolverClick = {
                    navController.navigate(Rutas.HOME)
                },
                onEditarPerfilClick = {
                    navController.navigate(Rutas.USER_EDIT_PROFILE)
                },
                onCerrarSesionClick = {
                    navController.navigate(Rutas.LOGIN){
                        popUpTo(Rutas.HOME){
                            inclusive=true
                        }
                    }
                }
            )
        }

        composable(Rutas.USER_EDIT_PROFILE) {
            PantallaUserEditProfile(
                onVolverClick = {
                    navController.navigate(Rutas.USER_PROFILE)
                },
                onPerfilActualizado = {
                    navController.navigate(Rutas.USER_PROFILE)
                }
            )
        }
    }
}