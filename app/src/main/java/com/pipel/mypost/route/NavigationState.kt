package com.pipel.mypost.route

import java.util.*

sealed class NavigationState {

    object Idle : NavigationState()

    data class Navigate(val route: String, val id: String = UUID.randomUUID().toString()) :
        NavigationState()

    data class NavigateUp(val id: String = UUID.randomUUID().toString()) : NavigationState()

}