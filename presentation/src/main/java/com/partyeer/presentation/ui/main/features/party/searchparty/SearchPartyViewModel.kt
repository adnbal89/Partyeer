package com.partyeer.presentation.ui.main.features.party.searchparty

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Tag
import com.partyeer.domain.repository.party.usecase.GetAllSearchTagsAndSubContents
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
    private val getAllSearchTagsAndSubContents: GetAllSearchTagsAndSubContents,
    state: SavedStateHandle
) : BaseViewModel() {
    private val currentQuery = state.getLiveData<String?>("currentQuery", null)

    //TODO: current query to be preserved for search bar
    val hasCurrentQuery = currentQuery.asFlow().map { it != null }

    private val _tagList = MutableStateFlow<List<Tag>>(
        mutableListOf()
    )
    val tagList: StateFlow<List<Tag>>
        get() = _tagList

    override fun onViewAttached() {
        super.onViewAttached()

        getAllSearchTagsAndSubContents(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect {
                        println(it)
                        _tagList.value = it
                    }
                }
            }
            onError = {
                println(it)
            }
        }
    }

    fun onSearchQuerySubmit(query: String) {

    }

}