package com.partyeer.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.partyeer.data.remote.store.party.remote.service.FirebaseRemotePartyService
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val firebaseDatabase = FirebaseRemotePartyService(Firebase.database, Firebase.storage)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}