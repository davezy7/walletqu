package idmy.davezy.walletqu.features.transaction.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import idmy.davezy.walletqu.core_navigation.features.TransactionNavigator
import idmy.davezy.walletqu.features.transaction.navigation.routes.CategoryListRoute
import idmy.davezy.walletqu.features.transaction.navigation.routes.RecordRoute
import idmy.davezy.walletqu.features.transaction.screen.CategoryListScreen
import idmy.davezy.walletqu.features.transaction.screen.RecordScreen
import javax.inject.Inject

class TransactionNavigatorImpl @Inject constructor() : TransactionNavigator(RecordRoute) {

    override fun getRecordRoute(isEdit: Boolean) = RecordRoute.getNavRoute(isEdit)

    override fun registerNavigation(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) = with(navGraphBuilder) {
        val navigator = this@TransactionNavigatorImpl

        RecordRoute.registerRoute(this) { RecordScreen(navigator = navigator) }

        CategoryListRoute.registerRoute(this) { CategoryListScreen() }
    }
}