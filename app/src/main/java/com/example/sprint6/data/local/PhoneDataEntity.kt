package com.example.sprint6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="product_table")
data class PhoneProductsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
)

@Entity(tableName="details_table")
data class PhoneDetailsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)