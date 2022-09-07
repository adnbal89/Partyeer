package com.partyeer.data.remote.store.party.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.partyeer.data.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.PartyResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val service: PartyService
) : PartyDataSource {

    override suspend fun getParty(id: String): PartyResponse {
        //TODO : service.getParty(id)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
        return PartyResponse(id)
    }
}