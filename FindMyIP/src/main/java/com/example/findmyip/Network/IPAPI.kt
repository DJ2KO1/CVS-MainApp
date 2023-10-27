package com.example.findmyip.Network

import com.example.findmyip.model.IPResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface IPAPI {
    @GET("json")
    suspend fun getIP(): Response<IPResponseModel>
}