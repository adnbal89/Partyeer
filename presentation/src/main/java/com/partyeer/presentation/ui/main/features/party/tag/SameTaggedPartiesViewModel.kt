package com.partyeer.presentation.ui.main.features.party.tag

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.GetPartiesTaggedBy
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SameTaggedPartiesViewModel @Inject constructor(
    private val getPartiesTaggedBy: GetPartiesTaggedBy,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _partyList = MutableStateFlow<List<Party>>(
        mutableListOf()
    )
    val partyList: StateFlow<List<Party>>
        get() = _partyList

    fun getPartiesTaggedBy(tag: String) {
        getPartiesTaggedBy(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect { list ->
                        _partyList.value = list
                    }
                }
            }
            onError = {

            }
            params = tag
        }
    }

}