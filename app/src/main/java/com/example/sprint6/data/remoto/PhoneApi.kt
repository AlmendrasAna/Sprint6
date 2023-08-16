package com.example.sprint6.data.remoto


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi{

    @GET("products")
    suspend fun getProductsData():Response<List<PhoneProductsData>>

    @GET("details/{id}")
    suspend fun getDetailsData(@Path("id")id:Int):Response<PhoneDetailsData>
}