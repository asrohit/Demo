package com.yotta.demo.network

import com.yotta.demo.model.RocketInfo
import com.yotta.demo.model.Rockets
import com.yotta.demo.model.RocketsItem
import okhttp3.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RocketApi {
    @GET("rockets")
    suspend fun getRocketsInfo() : Response<List<Rockets>>

    @GET("rockets/")
    fun getRocketInfoDetails(
        @Query("id") id: String,
    ) : Call<RocketInfo>

    companion object{
        operator fun invoke() : RocketApi
        {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spacexdata.com/v4/")
                .build()
                .create(RocketApi::class.java)
        }
    }
}