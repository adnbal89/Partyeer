package com.partyeer.presentation.ui.main.features.profile

import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPartyList: GetAllParties,
) : BaseViewModel() {

    override fun onViewAttached() {
        super.onViewAttached()
            getPartyList(this) {
                onSuccess = {

                }
                onError = {

                }
            }
        }

}