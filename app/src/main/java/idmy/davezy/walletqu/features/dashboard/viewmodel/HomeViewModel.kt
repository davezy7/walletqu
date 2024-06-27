package idmy.davezy.walletqu.features.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import idmy.davezy.walletqu.core_entity.dashboard.DashboardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _dashboardDataState: MutableStateFlow<DashboardModel?> = MutableStateFlow(null)
    val dashboardDataState = _dashboardDataState.asStateFlow()

}