package idmy.davezy.walletqu.core_navigation.features

import idmy.davezy.walletqu.core_navigation.base.BaseNavigator
import idmy.davezy.walletqu.core_navigation.base.BaseRoute
import idmy.davezy.walletqu.core_navigation.Graphs.TRANSACTION_GRAPH

abstract class TransactionNavigator(initialRoute: BaseRoute) :
    BaseNavigator(graphRoute = TRANSACTION_GRAPH, initialRoute = initialRoute) {

    abstract fun getRecordRoute(isEdit: Boolean): String
}