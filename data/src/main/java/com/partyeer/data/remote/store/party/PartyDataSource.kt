package com.partyeer.data.remote.store.party

import com.partyeer.data.remote.store.party.remote.model.PartyResponse

interface PartyDataSource {

    suspend fun getParty(id: String): PartyResponse
}