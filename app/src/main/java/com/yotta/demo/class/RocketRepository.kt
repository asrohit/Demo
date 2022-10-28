package com.yotta.demo.`class`

import com.yotta.demo.network.RocketApi

class RocketRepository (private val api : RocketApi) : SafeAPIRequest(){

    suspend fun getRocket() = apiRequest { api.getRocketsInfo() }


}