package idmy.davezy.walletqu.core_ui.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.convertToDateString(datePattern: String = "dd MMM, yyyy"): String {
    val dateFromMillis = Instant
        .ofEpochMilli(this)
        .atZone(systemDefault())
        .toLocalDate()
    return DateTimeFormatter.ofPattern(datePattern, Locale.getDefault()).format(dateFromMillis)
}