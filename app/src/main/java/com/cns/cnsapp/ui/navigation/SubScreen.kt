package com.cns.cnsapp.ui.navigation

sealed class SubScreen {
    data object RecentActivities : SubScreen()
    data object ConnectedApps : SubScreen()
    data object NewRequests : SubScreen()
    data object ConfigureAccount : SubScreen()
}
