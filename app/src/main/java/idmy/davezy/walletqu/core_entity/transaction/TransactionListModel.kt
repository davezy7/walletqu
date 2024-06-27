package idmy.davezy.walletqu.core_entity.transaction

import java.math.BigDecimal

data class TransactionListModel(
    val category: String,
    val sourceOfFund: String,
    val date: String,
    val amount: BigDecimal,
    val note: String
)
