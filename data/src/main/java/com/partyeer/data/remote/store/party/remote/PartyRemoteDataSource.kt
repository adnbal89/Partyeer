package com.partyeer.data.remote.store.party.remote

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.partyeer.data.PartyDataSource
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Picture
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val service: PartyService,
) : PartyDataSource {

    private var database: FirebaseDatabase = Firebase.database
    private var firebaseStorageReference: StorageReference = Firebase.storage.reference

    val myRef = database.getReference("party")
    val list = arrayListOf<PartyDTO>()

    override suspend fun getPartyList(): Flow<List<PartyDTO>> =
        callbackFlow {

            val callback = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    list.clear()
                    for (snapshot in dataSnapshot.children) {
                        val party = snapshot.getValue(PartyDTO::class.java)
                        party?.let { list.add(it) }
                    }
                    trySend(list) //after this i dont get anything on the collect{} block
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.i(databaseError.message)
                }
            }

            try {
                myRef.addValueEventListener(callback)
            } catch (e: FirebaseException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {
                Timber.i("Closing")
                myRef.removeEventListener(callback)
            }
        }

    override suspend fun getParty(id: String): PartyDTO {
        //TODO : service.getParty(id)
        val partyDTO = list.filter {
            it.id == id
        }[0]
        return partyDTO
    }

    override suspend fun createParty(partyDTO: PartyDTO) {
        createNewParty(partyDTO)
    }

    private fun createNewParty(partyDTO: PartyDTO) {

        partyDTO.pictures.forEach {
            try {
                firebaseStorageReference.child("images/house.jpg").putFile(Uri.parse(it.fullSize))
                    .addOnSuccessListener {
                        partyDTO.pictures.clear()
                        firebaseStorageReference.child("images/house.jpg").downloadUrl.addOnSuccessListener { uri ->
                            partyDTO.pictures.add(Picture(uri.toString(), uri.toString()))
                            myRef.child(partyDTO.id).setValue(partyDTO)
                        }
                    }


            } catch (e: Exception) {
                println("adnan error : " + e.localizedMessage)
            }
        }
        //database.child("parties").child(partyDTO.id).setValue(partyDTO.id)
    }

    fun uploadToFirebaseStorage(uri: Uri) {

    }
}


/*PartyDTO(
           "1",
           "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
           "First Party",
           "Techno",
           123213123.0
       )*/