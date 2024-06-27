package idmy.davezy.walletqu.core_entity.dashboard

import idmy.davezy.walletqu.core_entity.transaction.TransactionListModel
import java.math.BigDecimal

data class DashboardModel(
    val totalBalance: BigDecimal,
    val lastTransactions: List<TransactionListModel>
)