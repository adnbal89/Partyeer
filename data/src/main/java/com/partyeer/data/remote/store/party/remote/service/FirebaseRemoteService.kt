package com.partyeer.data.remote.store.party.remote.service

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.util.formatter.FileNameFormatter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteService @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference,
    private val firebaseStorage: FirebaseStorage
) : PartyRemoteService {

    private val list = arrayListOf<PartyDTO>()
    private var listSizeCounter = 0

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
        val copyPartDTO = partyDTO.copy(pictures = mutableListOf())
        listSizeCounter = partyDTO.pictures.size

        partyDTO.pictures.forEach { picture ->
            try {
                val storageRef =
                    firebaseStorage.getReference("images/" + FileNameFormatter.getFormattedFileName())

                storageRef.putFile(Uri.parse(picture.fullSize))
                    .addOnSuccessListener {
                        storageRef.downloadUrl.addOnSuccessListener { uri ->
                            //when a picture uploaded to Storage successfully then put it into
                            //partyDTO pictures List so that partyDTO will be uploaded to realtime DB
                            copyPartDTO.pictures.add(Picture(uri.toString(), uri.toString()))

                            //send partyDTO to realtime database when all pictures are uploaded successfully.
                            if (copyPartDTO.pictures.size == listSizeCounter && copyPartDTO.pictures.size > 0) {
                                copyPartDTO.logoUrl = copyPartDTO.pictures.get(0).preview
                                createFirebaseEntry(copyPartDTO)
                            }
                        }
                    }
            } catch (e: Exception) {
                Timber.e(e.localizedMessage)
            }
        }
    }

    private fun createFirebaseEntry(partyDTO: PartyDTO) {
        firebaseDatabaseReference.child(partyDTO.id).setValue(partyDTO).addOnSuccessListener {
            //TODO : implement Flow so that activity finish() when upload completes.

        }
    }
}