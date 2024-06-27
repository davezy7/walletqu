package idmy.davezy.walletqu.features.transaction.screen

import android.content.Context
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Number
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.hilt.navigation.compose.hiltViewModel
import idmy.davezy.walletqu.R
import idmy.davezy.walletqu.core_entity.others.ToggleTabModel
import idmy.davezy.walletqu.core_navigation.features.TransactionNavigator
import idmy.davezy.walletqu.core_ui.components.WQTextField
import idmy.davezy.walletqu.core_ui.components.WQTopAppBar
import idmy.davezy.walletqu.core_ui.theme.Body2
import idmy.davezy.walletqu.core_ui.theme.Body3
import idmy.davezy.walletqu.core_ui.theme.BodySemiBold2
import idmy.davezy.walletqu.core_ui.theme.DarkGray40
import idmy.davezy.walletqu.core_ui.theme.Scarlet
import idmy.davezy.walletqu.core_ui.theme.SpringGreen
import idmy.davezy.walletqu.core_ui.theme.Title1
import idmy.davezy.walletqu.core_ui.theme.White
import idmy.davezy.walletqu.core_ui.utils.Dp0
import idmy.davezy.walletqu.core_ui.utils.Dp12
import idmy.davezy.walletqu.core_ui.utils.Dp16
import idmy.davezy.walletqu.core_ui.utils.Dp32
import idmy.davezy.walletqu.core_ui.utils.Dp4
import idmy.davezy.walletqu.core_ui.utils.Dp6
import idmy.davezy.walletqu.core_ui.utils.Dp8
import idmy.davezy.walletqu.core_ui.utils.clickableWithoutRipple
import idmy.davezy.walletqu.core_ui.utils.collectValue
import idmy.davezy.walletqu.core_ui.utils.convertToDateString
import idmy.davezy.walletqu.core_ui.utils.currencies.rememberCurrencyVisualTransformation
import idmy.davezy.walletqu.core_ui.utils.ifFalseThenShow
import idmy.davezy.walletqu.core_ui.utils.ifTrueThenShow
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.MINUS
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.PLUS
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.ZERO_STRING
import idmy.davezy.walletqu.core_ui.utils.strings.getString
import idmy.davezy.walletqu.core_ui.utils.strings.getStringResource
import idmy.davezy.walletqu.core_ui.utils.strings.ifEmpty
import idmy.davezy.walletqu.features.transaction.screen.TransactionScreenAttr.EXPENSE
import idmy.davezy.walletqu.features.transaction.screen.TransactionScreenAttr.IMG_DATE_EDIT
import idmy.davezy.walletqu.features.transaction.screen.TransactionScreenAttr.IMG_NOTE_EDIT
import idmy.davezy.walletqu.features.transaction.screen.TransactionScreenAttr.INCOME
import idmy.davezy.walletqu.features.transaction.viewmodel.RecordViewModel
import java.lang.System.currentTimeMillis

@Composable
internal fun RecordScreen(
    navigator: TransactionNavigator,
    viewModel: RecordViewModel = hiltViewModel()
) = with(viewModel) {
        Column(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current

            val amount = amountState.collectValue()
            val category = categoryState.collectValue()
            val currencyCode = currencyCodeState.collectValue()
            val note = noteState.collectValue()

            val selectedKey = remember { mutableStateOf(INCOME) }
            val isIncome by remember { derivedStateOf { selectedKey.value == INCOME } }

            WQTopAppBar(title = getString(res = R.string.transactionScreenAddRecordLabel))
            Spacer(modifier = Modifier.height(Dp16))
            AmountSection(
                toggleItems = getToggleItems(context),
                selectedKey = selectedKey.value,
                currencyCode = currencyCode,
                amount = amount,
                isIncome = isIncome,
                onToggleItemClicked = { selectedKey.value = it },
                onCurrencyCodeChanged = ::updateCurrencyCodeState,
                onAmountChanged = ::updateAmountState
            )
            Spacer(modifier = Modifier.height(Dp16))
            AdditionalInformationSection(
                noteValue = note,
                category = category,
                onCategoryClicked = { /*TODO: Nav to Category Screen*/ },
                onNoteValueChanged = ::updateNoteState,
                onDateChanged = ::updateDateState
            )
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp12),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = Dp0),
                shape = RoundedCornerShape(Dp4),
                colors = ButtonDefaults.elevatedButtonColors().copy(
                    containerColor = SpringGreen
                ),
                enabled = amount.text != ZERO_STRING || amount.text.isNotBlank(),
                onClick = ::saveTransaction,
            ) {
                Text(
                    text = getString(res = R.string.transactionScreenSaveLabel),
                    style = BodySemiBold2
                )
            }
        }
    }

@Composable
private fun AmountSection(
    toggleItems: List<ToggleTabModel>,
    selectedKey: String,
    amount: TextFieldValue,
    currencyCode: TextFieldValue,
    isIncome: Boolean,
    onToggleItemClicked: (key: String) -> Unit,
    onCurrencyCodeChanged: (TextFieldValue) -> Unit,
    onAmountChanged: (TextFieldValue) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(start = Dp12, end = Dp12, top = Dp16, bottom = Dp8),
        horizontalArrangement = SpaceEvenly,
        verticalAlignment = CenterVertically
    ) {
        toggleItems.forEach { item ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(Dp8))
                    .then(
                        if (selectedKey == item.key) Modifier.background(DarkGray40)
                        else Modifier
                    )
                    .padding(vertical = Dp6)
                    .clickableWithoutRipple { onToggleItemClicked(item.key) }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.title,
                    style = Body3.copy(textAlign = Center)
                )
            }
        }
    }
    Row {
        WQTextField(
            modifier = Modifier.weight(3f),
            value = currencyCode,
            readOnly = true,
            onValueChange = onCurrencyCodeChanged
        )
        WQTextField(
            modifier = Modifier.weight(8f, true),
            value = amount,
            textStyle = Title1.copy(
                textAlign = End,
                color = if (isIncome) SpringGreen else Scarlet
            ),
            keyboardType = Number,
            visualTransformation = rememberCurrencyVisualTransformation(currencyCode.text),
            suffix = {
                Text(
                    text = if (isIncome) PLUS else MINUS,
                    style = Title1,
                    color = if (isIncome) SpringGreen else Scarlet,
                    textAlign = End
                )
            },
            onValueChange = onAmountChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdditionalInformationSection(
    noteValue: TextFieldValue,
    category: String,
    onCategoryClicked: () -> Unit,
    onNoteValueChanged: (TextFieldValue) -> Unit,
    onDateChanged: (String) -> Unit
) {
    var isPickingDate by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentTimeMillis())
    val dateString = remember {
        derivedStateOf {
            datePickerState.selectedDateMillis?.convertToDateString()
        }
    }

    LaunchedEffect(dateString) { dateString.value?.let(onDateChanged) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(Dp12)
            .clickableWithoutRipple(onClick = onCategoryClicked),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(Dp32),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
            contentDescription = IMG_DATE_EDIT,
        )
        Spacer(modifier = Modifier.width(Dp8))
        Text(text = getString(res = R.string.transactionScreenCategoryLabel), style = Body2)
        Text(
            modifier = Modifier.weight(1f),
            text = category.ifEmpty(getString(res = R.string.transactionScreenRequiredLabel)),
            textAlign = End,
            style = if (category.isBlank()) Body2 else Body2.copy(color = Scarlet),
        )
        Icon(
            modifier = Modifier.size(Dp32),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
            contentDescription = IMG_NOTE_EDIT,
        )
    }
    HorizontalDivider(
        modifier = Modifier
            .background(White)
            .padding(Dp4), color = DarkGray40
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(Dp12)
            .clickableWithoutRipple { isPickingDate = isPickingDate.not() },
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(Dp32),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
            contentDescription = IMG_DATE_EDIT,
        )
        Spacer(modifier = Modifier.width(Dp8))
        Text(text = getString(res = R.string.transactionScreenDateLabel), style = Body2)
        Text(
            modifier = Modifier.weight(1f),
            text = dateString.value.orEmpty(),
            textAlign = End,
            style = Body2,
        )
        isPickingDate.ifFalseThenShow {
            Icon(
                modifier = Modifier.size(Dp32),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                contentDescription = IMG_NOTE_EDIT,
            )
        }.ifTrueThenShow {
            Icon(
                modifier = Modifier.size(Dp32),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                contentDescription = IMG_NOTE_EDIT,
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .background(White)
            .padding(Dp4), color = DarkGray40
    )
    isPickingDate.ifTrueThenShow {
        DatePicker(
            state = datePickerState,
            modifier = Modifier.fillMaxWidth(),
            headline = null,
            showModeToggle = false,
            title = null
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(Dp12),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(Dp32),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_square),
            contentDescription = IMG_DATE_EDIT,
        )
        Spacer(modifier = Modifier.width(Dp8))
        Text(text = getString(res = R.string.transactionScreenNoteLabel), style = Body2)
        WQTextField(
            modifier = Modifier.weight(1f),
            value = noteValue,
            textStyle = Body2.copy(textAlign = End),
            onValueChange = onNoteValueChanged,
        )
        Icon(
            modifier = Modifier.size(Dp32),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
            contentDescription = IMG_NOTE_EDIT,
        )
    }
}

private fun getToggleItems(context: Context) = listOf(
    ToggleTabModel(
        key = INCOME,
        title = context.getStringResource(R.string.transactionScreenIncomeLabel)
    ),
    ToggleTabModel(
        key = EXPENSE,
        title = context.getStringResource(R.string.transactionScreenExpenseLabel)
    )
)

private object TransactionScreenAttr {
    const val INCOME = "income"
    const val EXPENSE = "expense"
    const val IMG_DATE_EDIT = "img_date_edit"
    const val IMG_NOTE_EDIT = "img_note_edit"
}