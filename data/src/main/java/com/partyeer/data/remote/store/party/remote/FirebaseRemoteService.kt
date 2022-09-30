package com.partyeer.data.remote.store.party.remote

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Picture
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteService @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference,
    private val firebaseStorageReference: StorageReference
) : PartyRemoteService {

    private val list = arrayListOf<PartyDTO>()

    override suspend fun getPartyList(): Flow<List<PartyDTO>> =
        callbackFlow {
            val callback = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    list.clear()
                    for (snapshot in dataSnapshot.children) {
                        val party = snapshot.getValue(PartyDTO::class.java)
                        party?.let { list.add(it) }
                    }
                    trySend(list)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.i(databaseError.message)
                }
            }

            try {
                firebaseDatabaseReference.addValueEventListener(callback)
            } catch (e: FirebaseException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {
                Timber.i("Closing")
                firebaseDatabaseReference.removeEventListener(callback)
            }
        }


    override suspend fun getParty(id: String): PartyDTO {
        TODO("Not yet implemented")
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        createNewParty(partyDTO)
    }

    override suspend fun getPartyConcepts(): Flow<List<ConceptDTO>> {
        TODO("Not yet implemented")
    }

    private fun createNewParty(partyDTO: PartyDTO) {
        partyDTO.pictures.forEach {
            try {
                firebaseStorageReference.putFile(Uri.parse(it.fullSize))
                    .addOnSuccessListener {
                        partyDTO.pictures.clear()
                        firebaseStorageReference.downloadUrl.addOnSuccessListener { uri ->
                            partyDTO.pictures.add(Picture(uri.toString(), uri.toString()))
                            firebaseDatabaseReference.child(partyDTO.id).setValue(partyDTO)
                        }
                    }
            } catch (e: Exception) {
                println("adnan error : " + e.localizedMessage)
            }
        }
    }
}