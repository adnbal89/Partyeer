package com.partyeer.data.remote.store.party.remote

import com.partyeer.data.remote.store.party.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.PartyResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val service: PartyService
): PartyDataSource {

    override suspend fun getParty(id: String): PartyResponse {
            //TODO : service.getParty(id)
        return PartyResponse(id)
    }
}