package idmy.davezy.walletqu.core_ui.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> StateFlow<T>.collectValue() = this.collectAsStateWithLifecycle().value