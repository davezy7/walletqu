package idmy.davezy.walletqu.features.transaction.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import idmy.davezy.walletqu.core_entity.transaction.TransactionModel
import idmy.davezy.walletqu.core_ui.utils.currencies.CurrencyConstants.USD_CODE
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.EMPTY_STRING
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.ZERO_STRING
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : ViewModel() {

    private val _amountState = MutableStateFlow(TextFieldValue(ZERO_STRING))
    val amountState = _amountState.asStateFlow()

    private val _categoryState = MutableStateFlow(EMPTY_STRING)
    val categoryState = _categoryState.asStateFlow()

    private val _currencyCodeState = MutableStateFlow(TextFieldValue(USD_CODE))
    val currencyCodeState = _currencyCodeState.asStateFlow()

    private val _noteState = MutableStateFlow(TextFieldValue(EMPTY_STRING))
    val noteState = _noteState.asStateFlow()

    private val _dateState = MutableStateFlow(EMPTY_STRING)

    fun updateAmountState(value: TextFieldValue) = _amountState.update { value }
    fun updateCategoryState(value: String) = _categoryState.update { value }
    fun updateCurrencyCodeState(value: TextFieldValue) = _currencyCodeState.update { value }
    fun updateDateState(value: String) = _dateState.update { value }
    fun updateNoteState(value: TextFieldValue) = _noteState.update { value }

    fun saveTransaction() {
        val transaction = TransactionModel(
            amount = _amountState.value.text.toBigDecimal(),
            category = _categoryState.value,
            date = _dateState.value,
            note = _noteState.value.text
        )
    }
}