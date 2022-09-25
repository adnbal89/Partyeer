package com.partyeer.presentation.ui.main.features.party.createparty

import android.app.Activity
import android.content.Intent
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

    private val _party = MutableStateFlow<Party>(
        Party(
            "1",
            "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
            "First Party",
            "Techno",
            123213123.0,
            arrayListOf()
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
        createParty(this) {
            onSuccess = {
                println("adnan : Party Created.")
            }
            params = party
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
}
