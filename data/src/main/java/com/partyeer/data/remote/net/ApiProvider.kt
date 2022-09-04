package com.partyeer.data.remote.net

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiProvider(
    endpoint: String
) {

    //TODO
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
            .build()
    }

    private fun createMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}