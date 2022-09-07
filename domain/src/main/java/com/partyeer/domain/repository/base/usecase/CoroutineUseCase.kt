package com.partyeer.domain.repository.base.usecase

import com.partyeer.util.functional.Callback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.Nothing
import kotlin.Throwable
import kotlin.onFailure
import kotlin.onSuccess
import kotlin.runCatching

abstract class CoroutineUseCase<Params, Response> : BaseUseCase<Params, Response> {

    private var job: Job? = null

    private var scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val dispatcher = Dispatchers.IO

    override fun execute(
        useCaseRunner: UseCaseRunner,
        callback: Callback<Response>?,
        params: Params?
    ) {

        if (job != null) {
            cancel()
        }

        job = scope.launch {
            flow {
                runCatching {
                    buildUseCase(params)
                }.onSuccess {
                    emit(Result.Success(it))
                }.onFailure {
                    emit(Result.Error(it))
                }
            }.flowOn(dispatcher)
                .onStart { callback?.onStart() }
                .onEach {
                    when (it) {
                        is Result.Error -> callback?.onError(it.error)
                        is Result.Success -> callback?.onSuccess(it.data)
                    }
                }.onCompletion {
                    callback?.onComplete()
                }.collect()
        }
    }

    override fun cancel() {
        job?.cancel()
        job = null
    }

    abstract suspend fun buildUseCase(params: Params?): Response
}

private sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
}