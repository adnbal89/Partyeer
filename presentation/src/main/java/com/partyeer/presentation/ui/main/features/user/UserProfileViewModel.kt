package com.partyeer.presentation.ui.main.features.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.partyeer.domain.repository.user.model.User
import com.partyeer.domain.repository.user.usecase.GetUserDetailsByUserName
import com.partyeer.presentation.ui.main.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserDetailsByUserName: GetUserDetailsByUserName,
) : BaseViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    fun getUserDetailByUserName(userName: String) {
        getUserDetailsByUserName(this) {
            onSuccess = {
                viewModelScope.launch {
                    it.collect {
                        _userData.value = it!!
                    }
                }
            }

            params = userName
        }
    }

}