package com.partyeer.data

import com.partyeer.data.remote.store.party.mapper.PartyDTOMapper
import com.partyeer.data.remote.store.party.mapper.PartyMapper
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import kotlinx.coroutines.delay
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
        println("adnan PartyDataRepository")

        val x = partyDataSource.getPartyList()
        val y = x.map {
            partyMapper.map(it)
        }
        return y
    }

    override suspend fun getParty(id: String): Party {
        //TODO: remove delay
        delay(1500)
        return partyMapper.map(partyDataSource.getParty(id))
    }

    override suspend fun createParty(party: Party) {
        delay(1000)
        partyDataSource.createParty(partyDTOMapper.map(party))
    }
}