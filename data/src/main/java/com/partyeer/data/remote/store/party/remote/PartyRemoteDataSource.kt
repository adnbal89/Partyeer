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
        val myRef = database.getReference("party")

        myRef.setValue("Hello, World!")
        return PartyDTO(
            "1",
            "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
            "First Party",
            "Techno",
            123213123.0
        )
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        createNewParty(partyDTO)
    }

    private fun createNewParty(partyDTO: PartyDTO) {

        println("adnan createNewParty: " + partyDTO.id)
        database.child("parties").child(partyDTO.id).setValue(partyDTO.id)
    }
}