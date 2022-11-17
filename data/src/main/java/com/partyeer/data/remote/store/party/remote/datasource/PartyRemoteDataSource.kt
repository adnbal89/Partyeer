package com.partyeer.data.remote.store.party.remote.datasource

import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.data.remote.store.party.remote.service.PartyRemoteService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val partyRemoteService: PartyRemoteService
) : PartyDataSource {

    override suspend fun getPartyList(): Flow<List<PartyDTO>> = partyRemoteService.getPartyList()

    override suspend fun getParty(id: String) = partyRemoteService.getParty(id)

    override suspend fun createParty(partyDTO: PartyDTO) {
        partyRemoteService.createParty(partyDTO)
    }

    override suspend fun getPartyConcepts(): Flow<List<ConceptDTO>> =
        partyRemoteService.getPartyConcepts()

    override suspend fun applyToParty(partyId: String) {
        partyRemoteService.applyToParty(partyId)
    }

    override suspend fun getPartiesTaggedBy(tag: String): Flow<List<PartyDTO>> =
        partyRemoteService.getPartiesTaggedBy(tag)


}
