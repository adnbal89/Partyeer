package com.partyeer.data.remote.store.party.remote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseService @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference,
    private val firebaseStorageReference: StorageReference
): PartyService {

    override suspend fun getPartyList(): Flow<List<PartyDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getParty(id: String): PartyDTO {
        TODO("Not yet implemented")
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun getPartyConcepts(): Flow<List<ConceptDTO>> {
        TODO("Not yet implemented")
    }
}