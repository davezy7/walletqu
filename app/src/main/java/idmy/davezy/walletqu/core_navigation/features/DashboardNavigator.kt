package idmy.davezy.walletqu.core_navigation.features

import idmy.davezy.walletqu.core_navigation.base.BaseNavigator
import idmy.davezy.walletqu.core_navigation.base.BaseRoute
import idmy.davezy.walletqu.core_navigation.Graphs.DASHBOARD_GRAPH


abstract class DashboardNavigator(initialRoute: BaseRoute) :
    BaseNavigator(graphRoute = DASHBOARD_GRAPH, initialRoute = initialRoute)