package com.partyeer.data

import com.partyeer.data.remote.store.party.remote.model.PartyDTO

interface PartyDataSource {

    suspend fun getParty(id: String): PartyDTO

    suspend fun createParty(partyDTO: PartyDTO)
}