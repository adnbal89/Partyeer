package com.partyeer.data.remote.store.party.remote

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.partyeer.data.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.util.formatter.FileNameFormatter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val firebaseService: PartyRemoteService
) : PartyDataSource {

    //add to DI
    private var database: FirebaseDatabase = Firebase.database

    //add to DI
    private var firebaseStorageReference: StorageReference =
        Firebase.storage.getReference("images/${FileNameFormatter.getFormattedFileName()}")


    val myRef = database.getReference("party")

    val list = arrayListOf<PartyDTO>()

    override suspend fun getPartyList(): Flow<List<PartyDTO>> = firebaseService.getPartyList()

    override suspend fun getParty(id: String): PartyDTO {
        val partyDTO = firebaseService.getParty(id)
        /*//TODO : service.getParty(id)
        val partyDTO = list.filter {
            it.id == id
        }[0]*/
        return partyDTO
    }

    override suspend fun createParty(partyDTO: PartyDTO) = firebaseService.createParty(partyDTO)

    override suspend fun getPartyConcepts(): Flow<List<ConceptDTO>> = firebaseService.getPartyConcepts()

}
