package com.partyeer.presentation.ui.main.features.party

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.domain.repository.party.usecase.GetParty
import com.partyeer.presentation.ui.main.base.BaseViewModel
import com.partyeer.presentation.ui.main.features.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreatePartyViewModel @Inject constructor(
    private val getParty: GetAllParties
): BaseViewModel() {

    private val _party = MutableStateFlow<Party>(Party("Initial"))
    val party: StateFlow<Party>
        get() = _party


    override fun onViewAttached() {
        getParty(this) {
            onSuccess = {
                _party.value = it
            }
            params = GetAllParties.Params(DEFAULT_PARTY_ID)
        }


    }
    companion object {
        private const val DEFAULT_PARTY_ID = "From Network"
    }
}
