package com.partyeer.domain.repository.base.usecase

import com.partyeer.util.functional.Callback

interface UseCaseRunner {
    fun cancelUseCases()

    fun <T> callback(
        showLoading: Boolean,
        onStart: (() -> Unit)?,
        onSuccess: (T) -> Unit,
        onError: ((Throwable) -> Unit)?,
        onComplete: (() -> Unit)?
    ): Callback<T> = Callback(
        onStart = onStart,
        onSuccess = onSuccess,
        onError = onError,
        onComplete = onComplete
    )

    fun subscribeToCancel(onCancel: (Boolean) -> Unit)
}

class UseCaseRunnerDelegate : UseCaseRunner {
    private val onCancelCallbacks = arrayListOf<OnCancel>()

    override fun cancelUseCases() {
        val iterator = onCancelCallbacks.iterator()
        while (iterator.hasNext()) {
            iterator.next().invoke(true)
            iterator.remove()
        }
    }

    override fun subscribeToCancel(onCancel: (Boolean) -> Unit) {
        onCancelCallbacks.add(onCancel)
    }
}

typealias OnCancel = (Boolean) -> Unit