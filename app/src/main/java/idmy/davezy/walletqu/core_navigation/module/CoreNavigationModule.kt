package idmy.davezy.walletqu.core_navigation.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idmy.davezy.walletqu.core_navigation.CoreNavigator
import idmy.davezy.walletqu.core_navigation.features.DashboardNavigator
import idmy.davezy.walletqu.core_navigation.features.TransactionNavigator

@Module
@InstallIn(SingletonComponent::class)
class CoreNavigationModule {

    @Provides
    fun provideCoreNavigator(
        dashboardNavigator: DashboardNavigator,
        transactionNavigator: TransactionNavigator
    ) = CoreNavigator(
        listOf(
            dashboardNavigator,
            transactionNavigator
        )
    )
}