package com.partyeer.data.remote.store.party.remote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.partyeer.data.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val service: PartyService,
) : PartyDataSource {

    private var database: DatabaseReference = Firebase.database.reference


    override suspend fun getParty(id: String): PartyDTO {
        //TODO : service.getParty(id)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
        return PartyDTO(id)
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        createNewParty(partyDTO)
    }

    private fun createNewParty(partyDTO: PartyDTO) {

        println("adnan createNewParty: " + partyDTO.id)
        database.child("parties").child(partyDTO.id).setValue(partyDTO.id)
    }
}