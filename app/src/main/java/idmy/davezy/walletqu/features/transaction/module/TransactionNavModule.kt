package idmy.davezy.walletqu.features.transaction.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idmy.davezy.walletqu.core_navigation.features.TransactionNavigator
import idmy.davezy.walletqu.features.transaction.navigation.TransactionNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionNavModule {

    @Binds
    abstract fun bindTransactionNavigation(impl: TransactionNavigatorImpl): TransactionNavigator

}