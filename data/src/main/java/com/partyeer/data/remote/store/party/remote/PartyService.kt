package com.partyeer.data.remote.store.party.remote

import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PartyService {

    @GET("products/{id}")
    suspend fun getParty(
        @Path("id") id: String
    ): PartyDTO
}