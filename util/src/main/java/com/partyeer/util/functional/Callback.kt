package com.partyeer.util.functional

import timber.log.Timber

class Callback<T>(
    private val onStart: (() -> Unit)? = null,
    private val onComplete: (() -> Unit)? = null,
    private val onError: ((Throwable) -> Unit)? = null,
    private val onSuccess: ((T) -> Unit)? = null
) {
    fun onStart() {
        onStart?.invoke()
    }

    fun onSuccess(response: T) {
        onSuccess?.invoke(response)
    }

    fun onError(error: Throwable) {
        //Log all errors on callback
        Timber.e(error)
        onError?.invoke(error)
    }

    fun onComplete() {
        onComplete?.invoke()
    }
}