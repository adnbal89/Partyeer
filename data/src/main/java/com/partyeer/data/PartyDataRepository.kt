package com.partyeer.data

import com.partyeer.data.remote.store.party.mapper.PartyDTOMapper
import com.partyeer.data.remote.store.party.mapper.PartyMapper
import com.partyeer.data.remote.store.party.remote.datasource.PartyDataSource
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyDataRepository @Inject constructor(
    private val partyDataSource: PartyDataSource,
    private val partyMapper: PartyMapper,
    private val partyDTOMapper: PartyDTOMapper
) : PartyRepository {

    override suspend fun getPartyList(): Flow<List<Party>> {
        //delay(2500L)

        val x = partyDataSource.getPartyList()
        val y = x.map {
            partyMapper.map(it)
        }
        return y
    }

    override suspend fun getParty(id: String): Party {
        //TODO: remove delay
        //delay(1500)
        return partyMapper.map(partyDataSource.getParty(id))
    }

    override suspend fun createParty(party: Party) {
        //delay(1000)
        partyDataSource.createParty(partyDTOMapper.map(party))
    }

    override suspend fun getPartyConcepts(): Flow<List<Concept>> {
        TODO("Not yet implemented")
    }

    override suspend fun applyToParty(partyId: String) {
        partyDataSource.applyToParty(partyId)
    }

    override suspend fun getPartiesTaggedBy(tag: String): Flow<List<Party>> {

        val x = partyDataSource.getPartiesTaggedBy(tag)
        val y = x.map {
            partyMapper.map(it)
        }
        return y
    }

}