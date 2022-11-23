package com.partyeer.presentation.ui.main.features.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.partyeer.domain.repository.login.model.UserCredential
import com.partyeer.domain.repository.login.usecase.CheckUserAuthenticated
import com.partyeer.domain.repository.login.usecase.CreateUserWithEmail
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkUserAuthenticated: CheckUserAuthenticated,
    private val createUserWithEmail: CreateUserWithEmail,

    ) : BaseViewModel() {
    private val eventChannel = Channel<LoginStatus>()
    val events = eventChannel.receiveAsFlow()

    fun isUserLoggedIn() {

    }

    fun isUserValid(userCredential: UserCredential) {
        checkUserAuthenticated(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect {
                        if (it != null) eventChannel.send(LoginStatus.Success(it))
                        else eventChannel.send(LoginStatus.Error)
                    }
                }
            }

            onError = {
                viewModelScope.launch {
                    eventChannel.send(LoginStatus.Error)
                }
            }
            params = userCredential
        }
    }

    fun signupUser(userCredential: UserCredential) {
        createUserWithEmail(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect {
                        if (it != null) eventChannel.send(LoginStatus.Success(it))
                        else eventChannel.send(LoginStatus.Error)
                    }
                }
            }
            onError = {
                viewModelScope.launch {
                    eventChannel.send(LoginStatus.Error)
                }
            }
            params = userCredential
        }
    }

    sealed class LoginStatus {
        data class Success(val firebaseUser: FirebaseUser?) : LoginStatus()
        object Error : LoginStatus()
    }
}