package idmy.davezy.walletqu.core_ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import idmy.davezy.walletqu.R
import idmy.davezy.walletqu.core_ui.components.WQTopAppBarAttr.NAVIGATION_ICON_CONTENT_DESCRIPTION
import idmy.davezy.walletqu.core_ui.theme.BodySemiBold1
import idmy.davezy.walletqu.core_ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WQTopAppBar(title: String, onBackPressed: (() -> Unit)? = null) {
    val backPressDispatcher by rememberUpdatedState(
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    )
    CenterAlignedTopAppBar(
        title = { Text(text = title, style = BodySemiBold1) },
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed?.invoke() ?: backPressDispatcher?.onBackPressed() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nav_back),
                    contentDescription = NAVIGATION_ICON_CONTENT_DESCRIPTION
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = White)
    )
}

@Preview
@Composable
private fun WQTopAppBarPreview() {
    WQTopAppBar(title = "Top Bar") {}
}

object WQTopAppBarAttr {
    internal const val NAVIGATION_ICON_CONTENT_DESCRIPTION = "navigation_back_icon"
}