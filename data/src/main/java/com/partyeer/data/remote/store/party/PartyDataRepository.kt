package com.partyeer.data.remote.store.party

import com.partyeer.data.remote.store.party.remote.mapper.PartyResponseMapper
import com.partyeer.domain.repository.party.PartyRepository
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.util.exception.Failure
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyDataRepository @Inject constructor(
    private val partyDataSource: PartyDataSource,
    private val partyMapper: PartyResponseMapper
) : PartyRepository {

    override suspend fun getParty(id: String): Party {
        //TODO: remove delay
        delay(1500)
        return partyMapper.map(partyDataSource.getParty(id))
    }
}