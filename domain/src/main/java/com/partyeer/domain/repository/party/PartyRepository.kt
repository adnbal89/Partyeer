package com.partyeer.domain.repository.party

import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import kotlinx.coroutines.flow.Flow

interface PartyRepository : BaseRepository {

    suspend fun getPartyList(): Flow<List<Party>>

    suspend fun getParty(id: String): Party

    suspend fun createParty(party: Party)

    suspend fun getPartyConcepts(): Flow<List<Concept>>
}