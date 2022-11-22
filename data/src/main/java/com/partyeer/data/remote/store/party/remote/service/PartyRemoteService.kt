package com.partyeer.data.remote.store.party.remote.service

import com.partyeer.data.remote.RemoteService
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow

interface PartyRemoteService : RemoteService {

    suspend fun getPartyList(): Flow<List<PartyDTO>>

    suspend fun getParty(id: String): PartyDTO

    suspend fun createParty(partyDTO: PartyDTO)

    suspend fun getPartyConcepts(): Flow<List<ConceptDTO>>

    suspend fun applyToParty(partyId: String)

    suspend fun getPartiesTaggedBy(tag: String): Flow<List<PartyDTO>>
}