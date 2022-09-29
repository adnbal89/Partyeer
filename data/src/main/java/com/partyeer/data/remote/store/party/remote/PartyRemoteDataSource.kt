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
import com.partyeer.data.remote.store.party.remote.model.ConceptDTO
import com.partyeer.data.remote.store.party.remote.model.PartyDTO
import com.partyeer.domain.repository.party.model.Picture
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartyRemoteDataSource @Inject constructor(
    private val partyApiService: PartyService,
) : PartyDataSource {

    //add to DI
    private var database: FirebaseDatabase = Firebase.database

    //decide if inject or private property
    private var formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    private val now = Date()
    private val fileName = formatter.format(now)


    //add to DI
    private var firebaseStorageReference: StorageReference =
        Firebase.storage.getReference("images/${fileName}")


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
                    trySend(list)
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
        delay(1500L)
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
                            myRef.child(partyDTO.id).setValue(partyDTO)
                        }
                    }
            } catch (e: Exception) {
                println("adnan error : " + e.localizedMessage)
            }
        }
    }
}


/*PartyDTO(
           "1",
           "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
           "First Party",
           "Techno",
           123213123.0
       )*/