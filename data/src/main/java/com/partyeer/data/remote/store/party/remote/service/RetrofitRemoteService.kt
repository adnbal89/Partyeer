package com.partyeer.data.remote.store.party.remote.service

import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow

class RetrofitRemoteService : PartyRemoteService {

    override suspend fun getPartyList(): Flow<List<PartyDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getParty(id: String): PartyDTO {
        TODO("Not yet implemented")
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun getPartyConcepts(): Flow<List<ConceptDTO>> {
        TODO("Not yet implemented")
    }

}