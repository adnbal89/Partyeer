package com.partyeer.data.remote.store.party.base.remote.mapper

interface BaseMapper<Source, Target> {

    fun map(sources: Collection<Source>?, vararg extra: Any?): Collection<Target> {
        return sources?.mapNotNull {
            map(it, *extra)
        }.orEmpty()
    }

    fun map(source: Source, vararg extra: Any?): Target
}