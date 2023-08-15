package com.example.sprint6.data.remoto

data class PhoneProductsData(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)

data class PhoneDetailsData(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)

