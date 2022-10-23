package com.partyeer.presentation.ui.main.view.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(DelicateCoroutinesApi::class)
fun <T> LiveData<T>.asFlow(): Flow<T> = flow {
    val channel = Channel<T>(Channel.CONFLATED)
    val observer = Observer<T> {
        channel.trySend(it)
    }
    withContext(Dispatchers.Main.immediate) {
        observeForever(observer)
    }
    try {
        for (value in channel) {
            emit(value)
        }
    } finally {
        GlobalScope.launch(Dispatchers.Main.immediate) {
            removeObserver(observer)
        }
    }
}