package com.partyeer.data

import com.partyeer.data.remote.store.party.mapper.PartyDTOMapper
import com.partyeer.data.remote.store.party.mapper.PartyMapper
import com.partyeer.data.remote.store.party.mapper.TagMapper
import com.partyeer.data.remote.store.party.remote.datasource.PartyDataSource
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Concept
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyDataRepository @Inject constructor(
    private val partyDataSource: PartyDataSource,
    private val partyMapper: PartyMapper,
    private val partyDTOMapper: PartyDTOMapper,
    private val tagMapper: TagMapper,
) : PartyRepository {

    override suspend fun getPartyList(): Flow<List<Party>> {
        return partyDataSource.getPartyList().map {
            partyMapper.map(it)
        }
    }

    override suspend fun getParty(id: String): Party {
        return partyMapper.map(partyDataSource.getParty(id))
    }

    override suspend fun createParty(party: Party) {
        partyDataSource.createParty(partyDTOMapper.map(party))
    }

    override suspend fun getPartyConcepts(): Flow<List<Concept>> {
        TODO("Not yet implemented")
    }

    override suspend fun applyToParty(partyId: String, userName: String) {
        partyDataSource.applyToParty(partyId, userName)
    }

    override suspend fun getPartiesTaggedBy(tag: String): Flow<List<Party>> {
        return partyDataSource.getPartiesTaggedBy(tag).map {
            partyMapper.map(it)
        }
    }

    override suspend fun getAllSearchTagsAndSubContents(): Flow<List<Tag>> {
        return partyDataSource.getAllSearchTagsAndSubContents().map {
            tagMapper.map(it)
        }
    }

}