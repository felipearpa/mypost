package com.felipearpa.mypost.route

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface RouteNavigator {
    val navigationStateFlow: StateFlow<NavigationState>
    fun navigate(route: String)
    fun navigateUp()
    fun onNavigated(state: NavigationState)
}

class DefaultRouteNavigator : RouteNavigator {

    private val _navigationFlow: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override val navigationStateFlow: StateFlow<NavigationState>
        get() = _navigationFlow.asStateFlow()

    override fun navigate(route: String) {
        _navigationFlow.value = NavigationState.Navigate(route)
    }

    override fun navigateUp() {
        _navigationFlow.value = NavigationState.NavigateUp()
    }

    override fun onNavigated(state: NavigationState) {
        _navigationFlow.compareAndSet(state, NavigationState.Idle)
    }

}