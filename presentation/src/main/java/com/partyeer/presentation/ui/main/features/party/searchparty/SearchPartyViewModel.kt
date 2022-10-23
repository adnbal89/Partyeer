package com.partyeer.presentation.ui.main.features.party.searchparty

import androidx.lifecycle.SavedStateHandle
import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.presentation.ui.main.base.BaseViewModel
import com.partyeer.presentation.ui.main.view.extension.asFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchPartyViewModel @Inject constructor(
    private val getPartyList: GetAllParties,
    state: SavedStateHandle
) : BaseViewModel() {
    private val currentQuery = state.getLiveData<String?>("currentQuery", null)
    val hasCurrentQuery = currentQuery.asFlow().map { it != null }

    override fun onViewAttached() {
        super.onViewAttached()

        getPartyList(this) {
            onSuccess = {

            }
            onError = {

            }
        }
    }

    fun onSearchQuerySubmit(query: String) {

    }

}