package idmy.davezy.walletqu.features.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import idmy.davezy.walletqu.core_navigation.features.DashboardNavigator
import idmy.davezy.walletqu.features.dashboard.screen.HomeScreen
import javax.inject.Inject

class DashboardNavigatorImpl @Inject constructor() : DashboardNavigator(HomeRoute) {

    override fun registerNavigation(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) = with(navGraphBuilder) {
        val navigator = this@DashboardNavigatorImpl
        HomeRoute.registerRoute(this) { HomeScreen(navigator) }
    }
}