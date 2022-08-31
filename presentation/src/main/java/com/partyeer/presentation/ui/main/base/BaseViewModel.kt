package com.partyeer.presentation.ui.main.base

import androidx.lifecycle.ViewModel
import com.partyeer.util.exception.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    private val _failure: MutableStateFlow<Failure> = MutableStateFlow(Failure.UnknownError)
    val failure: StateFlow<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure

    }
}