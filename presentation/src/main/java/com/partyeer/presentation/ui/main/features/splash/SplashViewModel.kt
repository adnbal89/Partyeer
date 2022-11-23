package com.partyeer.presentation.ui.main.features.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    val firebaseUser = firebaseAuth.currentUser

    fun isLoggedIn(): Boolean {
        return (firebaseUser != null)
    }
}