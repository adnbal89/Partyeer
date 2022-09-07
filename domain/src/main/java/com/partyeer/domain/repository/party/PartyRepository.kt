package com.partyeer.domain.repository.party

import com.partyeer.domain.repository.base.BaseRepository
import com.partyeer.domain.repository.party.model.Party

interface PartyRepository : BaseRepository {

    suspend fun getParty(id: String): Party
}