package idmy.davezy.walletqu.core_navigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.AMPERSAND
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.EQUAL
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.QUESTION_MARK

abstract class BaseRoute(
    private val route: String,
    private val args: List<NamedNavArgument> = listOf()
) {
    val baseRoute = route + args.joinToString(separator = AMPERSAND, prefix = QUESTION_MARK) { "${it.name}$EQUAL{${it.name}}" }

    protected fun buildNavRoute(navArgs: List<Pair<String, Any?>> = listOf()): String =
        route + navArgs.joinToString(separator = AMPERSAND, prefix = QUESTION_MARK) { "${it.first}$EQUAL${it.second}" }

    open fun registerRoute(
        navGraphBuilder: NavGraphBuilder,
        content: @Composable (NavBackStackEntry) -> Unit
    ) = with(navGraphBuilder) {
        composable(route = baseRoute, arguments = args, content = content)
    }
}
