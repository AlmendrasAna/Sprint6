package com.example.sprint6.data

import androidx.lifecycle.LiveData
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneDetailsEntity
import com.example.sprint6.data.local.PhoneProductsEntity
import com.example.sprint6.data.remoto.PhoneApi
import com.example.sprint6.data.remoto.PhoneDetailsData


class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneProductsListEntity(): LiveData<List<PhoneProductsEntity>> = phoneDao.getPhoneEntity()

    suspend fun loadDetailsPhone() {
        val response = phoneApi.getDetailsData()
        if (response.isSuccessful) {
            val bodyResponse = response.body()

            bodyResponse?.let { phoneData ->
                val phoneDetailsEntity = phoneData.map {
                    it.toDetailsEntity()
                }
                phoneDao.insertsListDetailsEntity(phoneDetailsEntity)
            }
        }

    }

}

fun PhoneDetailsData.toProductEntity(): PhoneProductsEntity = PhoneProductsEntity(
    this.id,
    this.name,
    this.price,
    this.image
)
fun PhoneDetailsData.toDetailsEntity(): PhoneDetailsEntity = PhoneDetailsEntity(
    this.id,
    this.name,
    this.price,
    this.image,
    this.description,
    this.lastPrice,
    this.credit
)