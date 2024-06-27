package idmy.davezy.walletqu.core_ui.utils

import androidx.compose.runtime.Composable

fun Boolean.ifTrueThen(action: () -> Unit) : Boolean {
    if (this) action()
    return this
}

fun Boolean.ifFalseThen(action: () -> Unit) : Boolean {
    if (!this) action()
    return this
}


@Composable
fun Boolean.ifTrueThenShow(content: @Composable () -> Unit) : Boolean {
    if (this) content()
    return this
}

@Composable
fun Boolean.ifFalseThenShow(content: @Composable () -> Unit) : Boolean {
    if (!this) content()
    return this
}