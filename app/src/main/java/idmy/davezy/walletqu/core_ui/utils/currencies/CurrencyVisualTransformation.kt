package idmy.davezy.walletqu.core_ui.utils.currencies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.core.text.isDigitsOnly
import java.text.NumberFormat.getCurrencyInstance
import java.util.Currency.getInstance

private class CurrencyVisualTransformation(
    currencyCode: String
) : VisualTransformation {
    private val numberFormatter = getCurrencyInstance().apply {
        currency = getInstance(currencyCode)
        maximumFractionDigits = 0
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text.trim()
        if (originalText.isEmpty().or(originalText.isDigitsOnly().not())) {
            return TransformedText(text, OffsetMapping.Identity)
        }
        val formattedText = numberFormatter.format(originalText.toBigDecimal())
        return TransformedText(
            AnnotatedString(formattedText),
            CurrencyOffsetMapping(originalText, formattedText)
        )
    }
}

class CurrencyOffsetMapping(originalText: String, formattedText: String) : OffsetMapping {
    private val originalLength: Int = originalText.length
    private val indexes = findDigitIndexes(originalText, formattedText)
    private fun findDigitIndexes(firstString: String, secondString: String): List<Int> {
        val digitIndexes = mutableListOf<Int>()
        var currentIndex = 0
        for (digit in firstString) {
            // Find the index of the digit in the second string
            val index = secondString.indexOf(digit, currentIndex)
            if (index != -1) {
                digitIndexes.add(index)
                currentIndex = index + 1
            } else {
                // If the digit is not found, return an empty list
                return emptyList()
            }
        }
        return digitIndexes
    }
    override fun originalToTransformed(offset: Int): Int {
        if (offset >= originalLength) {
            return indexes.last() + 1
        }
        return indexes[offset]
    }
    override fun transformedToOriginal(offset: Int): Int {
        return indexes.indexOfFirst { it >= offset }.takeIf { it != -1 } ?: originalLength
    }
}

@Composable
fun rememberCurrencyVisualTransformation(currencyCode: String): VisualTransformation {
    val inspectionMode = LocalInspectionMode.current
    return remember(currencyCode) {
        if (inspectionMode) None
        else CurrencyVisualTransformation(currencyCode)
    }
}