package com.partyeer.data.local.store.party.local

import com.partyeer.data.remote.store.party.remote.datasource.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyLocalDataSource @Inject constructor(
    private val dao: PartyDao
) : PartyDataSource {
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

    override suspend fun applyToParty(partyId: String) {
        TODO("Not yet implemented")
    }
}