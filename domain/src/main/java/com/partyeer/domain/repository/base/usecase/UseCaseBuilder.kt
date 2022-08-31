package com.partyeer.domain.repository.base.usecase

class UseCaseBuilder<Params, Response> {
    var showLoading: Boolean = true
    var params: Params? = null
    var onStart: (() -> Unit)? = null
    lateinit var onSuccess: (Response) -> Unit
    var onError: ((Throwable) -> Unit)? = null
    var onComplete: (() -> Unit)? = null
}