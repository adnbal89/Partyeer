package com.partyeer.presentation.ui.main.features.party.home

import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getParty: GetAllParties
) : BaseViewModel() {

    private val _partyList = MutableStateFlow<List<Party>>(
        mutableListOf()
    )
    val partyList: StateFlow<List<Party>>
        get() = _partyList


    override fun onViewAttached() {
        getParty(this) {
            onSuccess = {
                viewModelScope.launch {

                    it.collect { list ->
                        /*list.forEach {
                            println("adnan collect liffffst : " + list.size)
                            _party.value = it
                        }*/
                        _partyList.value = list
                    }
                }

            }
            onError = {

            }
        }


    }

    companion object {
        private const val DEFAULT_PARTY_ID = "From Network"
    }
}