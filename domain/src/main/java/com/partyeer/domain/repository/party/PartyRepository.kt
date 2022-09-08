package com.partyeer.domain.repository.party

import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.party.model.Party
import javax.inject.Singleton

interface PartyRepository : BaseRepository {

    suspend fun getParty(id: String): Party

    suspend fun insertParty(party: Party)
}