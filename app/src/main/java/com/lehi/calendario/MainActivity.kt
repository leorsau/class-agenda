package com.lehi.calendario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lehi.calendario.presentation.theme.CalendarioTheme
import com.lehi.calendario.presentation.views.PantallaEventDetails
import com.lehi.calendario.presentation.views.PantallaHome
import com.lehi.calendario.presentation.views.PantallaLogin
import com.lehi.calendario.presentation.views.PantallaRegister
import com.lehi.calendario.presentation.views.PantallaTaskDetails
import com.lehi.calendario.presentation.views.PantallaUserEditProfile
import com.lehi.calendario.presentation.views.PantallaUserProfile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalendarioTheme {



              // PantallaHome()

                // PantallaTaskDetails()

                // PantallaEventDetails()

                // PantallaUserProfile()

                // PantallaUserEditProfile()

                 PantallaLogin()

                //PantallaRegister()
            }
        }
    }
}