package com.partyeer.presentation.ui.main.base

interface BaseMapper<Source, Target> {

    fun map(sources: List<Source>?, vararg extra: Any?): List<Target> {
        return sources?.mapNotNull {
            map(it, *extra)
        }.orEmpty()
    }

    fun map(source: Source, vararg extra: Any?): Target
}