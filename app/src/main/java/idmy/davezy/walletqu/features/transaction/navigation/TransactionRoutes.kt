package idmy.davezy.walletqu.features.transaction.navigation

import androidx.navigation.NavType.Companion.BoolType
import androidx.navigation.navArgument
import idmy.davezy.walletqu.core_navigation.base.BaseRoute
import idmy.davezy.walletqu.features.transaction.navigation.RecordRoute.Args.IS_EDIT

internal object RecordRoute : BaseRoute(
    route = "record",
    args = listOf(navArgument(IS_EDIT) { type = BoolType })
) {

    fun getNavRoute(isEdit: Boolean) = buildNavRoute(listOf(IS_EDIT to isEdit))

    private object Args {
        const val IS_EDIT = "isEdit"
    }
}