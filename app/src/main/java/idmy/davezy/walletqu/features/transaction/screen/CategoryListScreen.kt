package idmy.davezy.walletqu.features.transaction.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import idmy.davezy.walletqu.R
import idmy.davezy.walletqu.core_ui.components.WQTopAppBar
import idmy.davezy.walletqu.core_ui.theme.Body2
import idmy.davezy.walletqu.core_ui.utils.Dp16
import idmy.davezy.walletqu.core_ui.utils.Dp8

@Composable
@Preview(showBackground = true, showSystemUi = true)
internal fun CategoryListScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { WQTopAppBar(title = "Select Category") }
        items(50) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp16),
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit_square),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(Dp8))
                Text(text = "Category Name", style = Body2)
            }
            HorizontalDivider()
        }
    }
}