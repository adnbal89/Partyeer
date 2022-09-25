package com.partyeer.data

import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow

interface PartyDataSource {

    suspend fun getPartyList(): Flow<List<PartyDTO>>

    suspend fun getParty(id: String): PartyDTO

    suspend fun createParty(partyDTO: PartyDTO)
}