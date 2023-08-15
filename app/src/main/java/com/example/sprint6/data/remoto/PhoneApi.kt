package com.example.sprint6.data.remoto


import retrofit2.Response
import retrofit2.http.GET

interface PhoneApi{

    @GET("/products")
    suspend fun getProductsData():Response<List<PhoneProductsData>>

    @GET("/details")
    suspend fun getDetailsData():Response<PhoneDetailsData>
}