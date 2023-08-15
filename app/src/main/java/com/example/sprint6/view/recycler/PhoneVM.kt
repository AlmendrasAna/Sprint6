package com.example.sprint6.view.recycler

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint6.data.Repository
import com.example.sprint6.data.local.PhoneDB
import com.example.sprint6.data.remoto.PhoneRFC
import kotlinx.coroutines.launch

class PhoneVM (application: Application): AndroidViewModel(application){

    private val repository : Repository

    fun phoneProductsLiveData () = repository.showPhoneProductsListEntity()
    fun getOneDetailsPhoneId(id : Int) = repository.showPhoneDetailsEntity(id)

    init {
        val api =   PhoneRFC.getRetrofitPhone()
        val phoneDB = PhoneDB.getDatabase(application).getDaoPhone()
        repository = Repository(api,phoneDB)
    }
    fun getAllProducts()= viewModelScope.launch{

        repository.loadProductsPhone()
    }


}

