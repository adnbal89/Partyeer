package com.partyeer.data.remote.store.party.remote.service

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.data.remote.store.party.remote.model.PictureDTO
import com.partyeer.util.formatter.FileNameFormatter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteService @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
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
                firebaseDatabase.getReference("party").addListenerForSingleValueEvent(callback)
            } catch (e: FirebaseException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {
                Timber.i("Closing")
                firebaseDatabase.getReference("party").removeEventListener(callback)
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

    override suspend fun applyToParty(partyId: String) {
        val party = firebaseDatabase.getReference("party").child(partyId)
        //TODO : implement dynamically.
        party.child("appliedUserIdList").child("adnbal89").setValue(true)
    }

    override suspend fun getPartiesTaggedBy(tag: String): Flow<List<PartyDTO>> =
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
                firebaseDatabase.getReference("tags").child(tag).child("parties")
                    .addListenerForSingleValueEvent(callback)
            } catch (e: FirebaseException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {
                Timber.i("Closing")
                firebaseDatabase.getReference("tags").child(tag).child("parties")
                    .removeEventListener(callback)
            }
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
                            copyPartDTO.pictures.add(PictureDTO(uri.toString(), uri.toString()))

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
        firebaseDatabase.getReference("party").child(partyDTO.id).setValue(partyDTO)
            .addOnSuccessListener {
            }
    }
}