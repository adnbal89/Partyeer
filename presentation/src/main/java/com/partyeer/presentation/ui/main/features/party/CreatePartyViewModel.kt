package com.partyeer.presentation.ui.main.features.party

import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.CreateParty
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreatePartyViewModel @Inject constructor(
    private val createParty: CreateParty
) : BaseViewModel() {

    private val _party = MutableStateFlow<Party>(Party("Initial"))
    val party: StateFlow<Party>
        get() = _party


    override fun onViewAttached() {
        createParty(this) {
            onSuccess = {
                println("adnan : Party Created.")
            }
            params = Party("New Party")
        }
    }

    companion object {
        private const val DEFAULT_PARTY_ID = "From Network"
    }
}
