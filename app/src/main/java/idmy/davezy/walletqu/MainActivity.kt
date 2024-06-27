package idmy.davezy.walletqu

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import idmy.davezy.walletqu.core_navigation.CoreNavigator
import idmy.davezy.walletqu.core_ui.theme.WalletQuTheme
import idmy.davezy.walletqu.features.transaction.screen.RecordScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coreNavigator: CoreNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletQuTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = coreNavigator.initialGraph
                ) {
                    coreNavigator.buildNavigation(
                        navController = navController,
                        navGraphBuilder = this
                    )
                }
            }
        }
    }
}