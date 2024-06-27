package idmy.davezy.walletqu.features.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults.bottomAppBarFabElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import idmy.davezy.walletqu.R
import idmy.davezy.walletqu.R.string
import idmy.davezy.walletqu.core_entity.transaction.TransactionListModel
import idmy.davezy.walletqu.core_navigation.features.DashboardNavigator
import idmy.davezy.walletqu.core_navigation.features.TransactionNavigator
import idmy.davezy.walletqu.core_ui.components.WQCard
import idmy.davezy.walletqu.core_ui.theme.Body2
import idmy.davezy.walletqu.core_ui.theme.Body4
import idmy.davezy.walletqu.core_ui.theme.BodySemiBold3
import idmy.davezy.walletqu.core_ui.theme.DarkGray40
import idmy.davezy.walletqu.core_ui.theme.JetBlack
import idmy.davezy.walletqu.core_ui.theme.Title1
import idmy.davezy.walletqu.core_ui.theme.Title4
import idmy.davezy.walletqu.core_ui.utils.Dp12
import idmy.davezy.walletqu.core_ui.utils.Dp16
import idmy.davezy.walletqu.core_ui.utils.Dp2
import idmy.davezy.walletqu.core_ui.utils.Dp32
import idmy.davezy.walletqu.core_ui.utils.Dp4
import idmy.davezy.walletqu.core_ui.utils.Dp8
import idmy.davezy.walletqu.core_ui.utils.collectValue
import idmy.davezy.walletqu.core_ui.utils.currencies.CurrencyConstants.USD_LOCALE
import idmy.davezy.walletqu.core_ui.utils.orZero
import idmy.davezy.walletqu.core_ui.utils.strings.StringConstants.EMPTY_STRING
import idmy.davezy.walletqu.core_ui.utils.strings.getString
import idmy.davezy.walletqu.core_ui.utils.toCurrency
import idmy.davezy.walletqu.features.dashboard.viewmodel.HomeViewModel
import java.math.BigDecimal

@Composable
internal fun HomeScreen(
    navigator: DashboardNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) = with(viewModel) {

    val dashboardData = dashboardDataState.collectValue()
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = Dp16),
                elevation = bottomAppBarFabElevation(),
                onClick = {
                    navigator.navigate(
                        navigator.get<TransactionNavigator>().getRecordRoute(true)
                    )
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                    contentDescription = EMPTY_STRING
                )
                Text(
                    text = getString(res = string.dashboardScreenAddRecord),
                    style = BodySemiBold3
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dp16)
                .padding(it),
            verticalArrangement = spacedBy(Dp16),
            horizontalAlignment = CenterHorizontally
        ) {
            TotalBalanceSection(dashboardData?.totalBalance.orZero())
            LastTransactionSection(dashboardData?.lastTransactions.orEmpty())
        }
    }
}

//region TOTAL-BALANCE-SECTION
@Composable
private fun TotalBalanceSection(totalBalance: BigDecimal) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = White,
            contentColor = Black,
            disabledContainerColor = Gray,
            disabledContentColor = Black
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = getString(res = string.dashboardScreenTotalBalance),
                style = Title4
            )
            HorizontalDivider(
                color = DarkGray40,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(text = totalBalance.toCurrency(USD_LOCALE), style = Title1)
        }
    }
}
//endregion

//region LAST-TRANSACTION-SECTION
@Composable
private fun LastTransactionSection(transactions: List<TransactionListModel>) {
    WQCard(modifier = Modifier.fillMaxWidth()) {
        Text(text = getString(res = string.dashboardScreenLastTransactions), style = Title4)
        HorizontalDivider(
            color = DarkGray40,
            modifier = Modifier.padding(vertical = Dp8)
        )
        if (transactions.isEmpty()) LastTransactionSectionEmpty()
        else {
            LazyColumn(
                verticalArrangement = spacedBy(Dp12)
            ) {
                items(transactions) { LastTransactionSectionItem(it) }
                item {
                    Text(
                        text = getString(res = string.dashboardScreenSeeAll),
                        modifier = Modifier.fillMaxWidth(),
                        style = BodySemiBold3,
                        textAlign = Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun LastTransactionSectionEmpty() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp32), contentAlignment = Alignment.Center
    ) { Text(text = getString(res = string.dashboardScreenEmptyTransactions), style = Body2) }
}

@Composable
private fun LastTransactionSectionItem(data: TransactionListModel) = with(data) {
    Row {
        Box(
            modifier = Modifier
                .padding(top = Dp2)
                .size(Dp32)
                .clip(RoundedCornerShape(percent = 50))
        )
        Spacer(modifier = Modifier.width(Dp8))
        Column(verticalArrangement = spacedBy(Dp4)) {
            Row(horizontalArrangement = SpaceBetween) {
                Text(text = category, style = Body2, modifier = Modifier.weight(1f))
                Text(text = amount.toString(), style = Body2, textAlign = End, color = Red)
            }
            Row(horizontalArrangement = SpaceEvenly) {
                Text(
                    text = sourceOfFund,
                    style = Body4,
                    modifier = Modifier.weight(1f),
                    color = JetBlack
                )
                Text(text = date, style = Body4, textAlign = End, color = JetBlack)
            }
            Text(text = note, style = Body4, color = JetBlack)
        }
    }
    HorizontalDivider(color = DarkGray40, modifier = Modifier.padding(top = Dp12))
}
//endregion