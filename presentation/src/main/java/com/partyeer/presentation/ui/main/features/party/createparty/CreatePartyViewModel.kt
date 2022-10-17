package com.partyeer.presentation.ui.main.features.party.createparty

import android.app.Activity
import android.content.Intent
import android.location.Location
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.usecase.CreateParty
import com.partyeer.domain.repository.party.usecase.GetPartyConceptList
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePartyViewModel @Inject constructor(
    private val createPartyUseCase: CreateParty,
    private val getPartyConceptList: GetPartyConceptList
) : BaseViewModel() {
    private val targetLocation = Location("") //provider name is unnecessary

    private val eventChannel = Channel<Event>()
    val events = eventChannel.receiveAsFlow()

    private val _party = MutableStateFlow<Party>(
        Party(
            "1",
            "",
            "Example",
            Concept("Techno"),
            40.254,
            28.987,
            1665264150,
            1665264150,
            "Example Description",
            mutableListOf(),
            likeCount = 51,
            mutableMapOf(),
            mutableMapOf(),
            mutableMapOf(),
            "1"
        )
    )
    val party: StateFlow<Party>
        get() = _party


    override fun onViewAttached() {

        /*val party = Party(
            "1",
            "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
            "First Party",
            "Techno",
            123213123.0,
            arrayListOf()
        )
        createParty(party)*/
    }

    fun createParty(party: Party) {
        createPartyUseCase(this) {
            onSuccess = {
                viewModelScope.launch {
                    eventChannel.send(Event.FinishActivity)
                }
            }
            params = party
        }
    }

    fun getConceptList() {
        getPartyConceptList(this) {
            onSuccess = {

            }
        }

    }

    fun selectImage(activity: Activity) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        activity.startActivityForResult(intent, 100)
    }

    companion object {
        private const val DEFAULT_PARTY_ID = "From Network"
    }

    sealed class Event {
        object FinishActivity : Event()
    }
}
