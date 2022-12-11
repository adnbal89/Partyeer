package com.partyeer.domain.repository.party

import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Tag
import kotlinx.coroutines.flow.Flow

interface PartyRepository : BaseRepository {

    suspend fun getPartyList(): Flow<List<Party>>

    suspend fun getParty(id: String): Party

    //change to flow<resource<party>> to be able
    // to handle if network operation is successful.
    suspend fun createParty(party: Party)

    suspend fun getPartyConcepts(): Flow<List<Concept>>

    suspend fun applyToParty(partyId: String)

    suspend fun getPartiesTaggedBy(tag: String): Flow<List<Party>>

    suspend fun getAllSearchTagsAndSubContents(): Flow<List<Tag>>
}