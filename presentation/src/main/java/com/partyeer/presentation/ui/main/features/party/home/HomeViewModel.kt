package com.partyeer.presentation.ui.main.features.party.home

import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.ApplyToParty
import com.partyeer.domain.repository.party.usecase.GetAllParties
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPartyList: GetAllParties,
    private val applyToParty: ApplyToParty
) : BaseViewModel() {

    private val _partyList = MutableStateFlow<List<Party>>(
        mutableListOf()
    )
    val partyList: StateFlow<List<Party>>
        get() = _partyList

    //events to be sent to fragment when triggered.
    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    override fun onViewAttached() {
        getPartyList(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect { list ->
                        /*list.forEach {
                            _party.value = it
                        }*/
                        _partyList.value = list
                    }
                }
            }
            onError = {
                viewModelScope.launch {
                    eventChannel.send(Event.ErrorOccurred(it))
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_PARTY_ID = "From Network"
    }

    fun applyToParty(partyId: String?) {
        applyToParty(this) {
            onSuccess = {
                viewModelScope.launch {
                    eventChannel.send(Event.PartyApplicationSuccessfullyCompleted)
                }
            }
            onError = {
                viewModelScope.launch {
                    eventChannel.send(Event.ErrorOccurred(it))
                }
            }
            params = partyId
        }
    }

    sealed class Event {
        object PartyApplicationSuccessfullyCompleted : Event()
        data class ErrorOccurred(val error: Throwable) : Event()
    }
}