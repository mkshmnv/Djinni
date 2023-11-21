package com.mkshmnv.djinni.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiService {
    @GET("gooDay")
    fun requestLink(@QueryMap map: Map<String, String>): Call<AnswerModel>
}