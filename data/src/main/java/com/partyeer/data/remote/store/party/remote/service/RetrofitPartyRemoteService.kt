package com.partyeer.data.remote.store.party.remote.service

import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.data.remote.store.party.remote.model.TagDTO
import kotlinx.coroutines.flow.Flow

class RetrofitPartyRemoteService : PartyRemoteService {

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

    override suspend fun applyToParty(partyId: String, userName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getPartiesTaggedBy(tag: String): Flow<List<PartyDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSearchTagsAndSubContents(): Flow<List<TagDTO>> {
        TODO("Not yet implemented")
    }

}