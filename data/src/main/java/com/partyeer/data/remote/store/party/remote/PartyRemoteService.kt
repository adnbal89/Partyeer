package com.partyeer.data.remote.store.party.remote

import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow

interface PartyRemoteService{

    suspend fun getPartyList(): Flow<List<PartyDTO>>

    suspend fun getParty(id: String): PartyDTO

    suspend fun createParty(partyDTO: PartyDTO)

    suspend fun getPartyConcepts(): Flow<List<ConceptDTO>>
}