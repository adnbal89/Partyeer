package com.partyeer.data.remote.store.login.remote.service

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.partyeer.data.remote.store.login.model.UserCredentialDTO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

class FirebaseRemoteLoginService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginRemoteService {

    override suspend fun isUserValid(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?> =
        callbackFlow {

            val signIn = firebaseAuth.signInWithEmailAndPassword(
                userCredentialDTO.userMail,
                userCredentialDTO.password
            )
            val callback =
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        trySend(task.result.user)
                    } else {
                        trySend(null)
                    }
                }
            try {
                signIn.addOnCompleteListener(callback)
            } catch (e: FirebaseAuthException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {

            }
        }

    override suspend fun createUserWithEmail(userCredentialDTO: UserCredentialDTO): Flow<FirebaseUser?> =
        callbackFlow {

            val signIn = firebaseAuth.createUserWithEmailAndPassword(
                userCredentialDTO.userMail,
                userCredentialDTO.password
            )
            val callback =
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        trySend(task.result.user)
                        sendEmailVerification(task.result.user)
                    } else {
                        trySend(null)
                    }
                }
            try {
                signIn.addOnCompleteListener(callback)
            } catch (e: FirebaseAuthException) {
                Timber.e(e.localizedMessage)
            }
            awaitClose {

            }
        }

    private fun sendEmailVerification(user: FirebaseUser?) {
        user?.sendEmailVerification()
    }
}