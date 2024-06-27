package idmy.davezy.walletqu.core_ui.utils

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal?.orZero(): BigDecimal = this ?: ZERO

fun BigDecimal.toCurrency(locale: Pair<String, String>): String =
    NumberFormat.getCurrencyInstance(Locale(locale.first, locale.second)).format(this)