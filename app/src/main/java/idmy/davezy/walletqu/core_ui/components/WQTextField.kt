package idmy.davezy.walletqu.core_ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.tooling.preview.Preview
import idmy.davezy.walletqu.core_ui.theme.Body2
import idmy.davezy.walletqu.core_ui.theme.White

@Composable
fun WQTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    maxLine: Int = 1,
    keyboardType: KeyboardType = Text,
    readOnly: Boolean = false,
    textStyle: TextStyle = Body2,
    visualTransformation: VisualTransformation = None,
    colors: TextFieldColors = TextFieldDefaults.colors().copy(
        unfocusedContainerColor = White,
        focusedContainerColor = White,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent
    ),
    suffix: @Composable (() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        colors = colors,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = keyboardType,
            imeAction = Done
        ),
        maxLines = maxLine,
        readOnly = readOnly,
        suffix = suffix,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange
    )
}

@Composable
@Preview
private fun WQTextFieldPreview() {
    WQTextField(value = TextFieldValue("This is a preview")) {
        
    }
}