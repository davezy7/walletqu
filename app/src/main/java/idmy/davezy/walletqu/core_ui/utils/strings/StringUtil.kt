package idmy.davezy.walletqu.core_ui.utils.strings

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.EMPTY_STRING
import java.text.NumberFormat
import java.util.Locale

fun Context.getStringResource(@StringRes res: Int) =
    try {
        getString(res)
    } catch (e: Exception) {
        EMPTY_STRING
    }

@Composable
fun getString(@StringRes res: Int) = LocalContext.current.getStringResource(res)


fun String.ifEmpty(replacementValue: String): String =
    if (isBlank().or(isEmpty())) replacementValue else this

fun String?.ifNullOrBlank(replacementValue: String): String =
    if (isNullOrBlank()) replacementValue else this