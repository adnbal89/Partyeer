package com.partyeer.presentation.ui.main.base

import androidx.lifecycle.ViewModel
import com.partyeer.domain.repository.base.usecase.UseCaseRunner
import com.partyeer.domain.repository.base.usecase.UseCaseRunnerDelegate
import com.partyeer.util.exception.Failure
import com.partyeer.util.functional.Callback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel(),
    UseCaseRunner by UseCaseRunnerDelegate() {

    val loading = MutableStateFlow<Boolean>(true)
    val error: MutableStateFlow<Throwable> = MutableStateFlow<Throwable>(Failure.DefaultError)

    override fun onCleared() {
        super.onCleared()
        cancelUseCases()
    }

    open fun onViewAttached() = Unit

    open fun onViewDetached() = Unit

    override fun <T> callback(
        showLoading: Boolean,
        onStart: (() -> Unit)?,
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)?,
        onComplete: (() -> Unit)?
    ): Callback<T> = Callback(
        onStart = {
            onStart?.invoke()
            if (showLoading) {
                loading.value = true
            }
        },
        onSuccess = {
            onSuccess(it)
        },
        onError = {
            if (onError != null) {
                if (it != Failure.DefaultError)
                    onError(it)
            } else {
                error.value = it
            }
        },
        onComplete = {
            onComplete?.invoke()
            if (showLoading) {
                loading.value = false
            }
        }
    )
}