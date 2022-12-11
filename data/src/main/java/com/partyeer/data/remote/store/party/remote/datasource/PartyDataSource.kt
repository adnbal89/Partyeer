package com.partyeer.data.remote.store.party.remote.datasource

import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.data.remote.store.party.remote.model.TagDTO
import kotlinx.coroutines.flow.Flow

interface PartyDataSource {

    suspend fun getPartyList(): Flow<List<PartyDTO>>

    suspend fun getParty(id: String): PartyDTO

    suspend fun createParty(partyDTO: PartyDTO)

    suspend fun getPartyConcepts(): Flow<List<ConceptDTO>>

    suspend fun applyToParty(partyId: String, userName: String)

    suspend fun getPartiesTaggedBy(tag: String): Flow<List<PartyDTO>>

    suspend fun getAllSearchTagsAndSubContents(): Flow<List<TagDTO>>
}