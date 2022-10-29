package com.partyeer.presentation.ui.main.features.party.searchparty

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.presentation.ui.main.base.BaseViewModel
import com.partyeer.presentation.ui.main.view.extension.asFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPartyViewModel @Inject constructor(
    private val getPartyList: GetAllParties,
    state: SavedStateHandle
) : BaseViewModel() {
    private val currentQuery = state.getLiveData<String?>("currentQuery", null)
    //TODO: current query to be preserved for search bar
    val hasCurrentQuery = currentQuery.asFlow().map { it != null }

    private val _partyList = MutableStateFlow<List<Party>>(
        mutableListOf()
    )
    val partyList: StateFlow<List<Party>>
        get() = _partyList

    override fun onViewAttached() {
        super.onViewAttached()

        getPartyList(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect { list ->
                        _partyList.value = list
                    }
                }
            }
            onError = {

            }
        }
    }

    fun onSearchQuerySubmit(query: String) {

    }

}