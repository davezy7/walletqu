package idmy.davezy.walletqu.core_entity.transaction

import java.math.BigDecimal

data class TransactionModel(
    val amount: BigDecimal,
    val category: String,
    val date: String,
    val note: String
)