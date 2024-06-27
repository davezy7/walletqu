package idmy.davezy.walletqu.features.dashboard.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idmy.davezy.walletqu.core_navigation.features.DashboardNavigator
import idmy.davezy.walletqu.features.dashboard.navigation.DashboardNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DashboardNavigationModule {

    @Binds
    abstract fun bindDashboardNavigation(impl: DashboardNavigatorImpl): DashboardNavigator
}