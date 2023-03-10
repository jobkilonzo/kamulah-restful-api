package com.example.kamulahtv.network

import com.example.kamulahtv.network.Program
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://kamulah-radio-api.onrender.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()
interface ProgramApi {
    @GET("/programs")
    fun getProgramAsync(): Deferred<List<Program>>
}

object NodejsApi {
    val programApi: ProgramApi by lazy {
        retrofit.create(ProgramApi::class.java)
    }
}