package com.example.sprint6.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneDetailsEntity
import com.example.sprint6.data.local.PhoneProductsEntity
import com.example.sprint6.data.remoto.PhoneApi
import com.example.sprint6.toDetaislEntity
import com.example.sprint6.toProductEntity


class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun showPhoneProductsListEntity(): LiveData<List<PhoneProductsEntity>> =
        phoneDao.showListProductsEntity()

    fun showPhoneDetailsEntity(id: Int): LiveData<PhoneDetailsEntity> =
        phoneDao.ShowDetailsEntityForId(id)

    suspend fun loadDetailsPhone(id:Int) {
        try {

            val response = phoneApi.getDetailsData(id)
            if (response.isSuccessful) {
                val bodyResponse = response.body()

                bodyResponse?.let { phoneData ->
                    val phoneDetailsEntity = phoneData.toDetaislEntity()
                    phoneDao.insertsOneDetailsEntity(phoneDetailsEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("catch", "Error")
        }
    }

    suspend fun loadProductsPhone() {
        try {

            val response = phoneApi.getProductsData()
            if (response.isSuccessful) {
                val bodyResponse = response.body()
                   bodyResponse?.let { phoneData ->
                    val phoneProductsEntity = phoneData.map {
                        it.toProductEntity()
                    }
                    phoneDao.insertsListProductsEntity(phoneProductsEntity)
                }
            }else {  Log.e("catch", "ERROR")}
        } catch (exception: Exception) {
            Log.e("catch", "ERROR")
        }
    }
}


