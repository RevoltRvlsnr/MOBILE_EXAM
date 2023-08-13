package com.exam.prayoga

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("stations2")
    fun getStations(): Call<List<Station>>
}