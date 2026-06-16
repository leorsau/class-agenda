package com.lehi.calendario.presentation.navigation

object Rutas{
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val TASK_DETAILS = "taskDetails"
    const val EVENT_DETAILS = "eventDetails"
    const val USER_PROFILE = "userProfile"
    const val USER_EDIT_PROFILE = "userEditProfile"

    fun taskDetailsConId(id: Int): String {
        return "$TASK_DETAILS/$id"
    }

    fun eventDetailsConId(id: Int): String {
        return "$EVENT_DETAILS/$id"
    }
}