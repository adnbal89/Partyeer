package com.partyeer.domain.repository.base.usecase

import com.partyeer.util.functional.Callback

interface BaseUseCase<Params, Response> {

    operator fun invoke(
        useCaseRunner: UseCaseRunner,
        useCaseBuilderBlock: UseCaseBuilder<Params, Response>.() -> Unit
    ) = execute(useCaseRunner, useCaseBuilderBlock)

    fun execute(
        useCaseRunner: UseCaseRunner,
        useCaseBuilderBlock: UseCaseBuilder<Params, Response>.() -> Unit
    ) {
        val useCaseBuilder = UseCaseBuilder<Params, Response>().apply(useCaseBuilderBlock)

        execute(
            useCaseRunner = useCaseRunner,
            callback = useCaseRunner.callback(
                showLoading = useCaseBuilder.showLoading,
                onStart = useCaseBuilder.onStart,
                onSuccess = useCaseBuilder.onSuccess,
                onError = useCaseBuilder.onError,
                onComplete = useCaseBuilder.onComplete
            ),
            params = useCaseBuilder.params
        )
    }

    fun execute(
        useCaseRunner: UseCaseRunner,
        callback: Callback<Response>?,
        params: Params? = null
    )

    fun cancel()


}