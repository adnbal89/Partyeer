package com.partyeer.data.remote.store.user.remote.service

import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.partyeer.data.remote.store.user.remote.model.UserDTO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

class FirebaseRemoteUserService @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) : UserRemoteService {
    override suspend fun getUserDetailsByUserName(userName: String): Flow<UserDTO?> = callbackFlow {
        val callback = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(UserDTO::class.java)
                trySend(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Timber.i(databaseError.message)
            }
        }

        try {
            firebaseDatabase.getReference("users").child(userName)
                .addListenerForSingleValueEvent(callback)
        } catch (e: FirebaseException) {
            Timber.e(e.localizedMessage)
        }
        awaitClose {
            Timber.i("Closing")
            firebaseDatabase.getReference("users").child(userName).removeEventListener(callback)
        }
    }
}