package com.partyeer.presentation.ui.main.features.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    //private val loginRepository: LoginRepository
) : ViewModel() {

    fun isLoggedIn() = false
}