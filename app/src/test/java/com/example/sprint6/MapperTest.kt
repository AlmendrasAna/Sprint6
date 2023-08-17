package com.example.sprint6

import com.example.sprint6.data.remoto.PhoneProductsData
import org.junit.Assert.*

import org.junit.Test

class MapperTest {

    @Test
    fun toProductEntity() {

        //given, Dado este valor
        val image = "http://federico.com/adasd.jpg"
        val id = 1
        val name = "Sansung pirata"
        val price = 100000


        val phoneProductsData=PhoneProductsData(id,name,price,image)

        //when, hago esta accion
        val resultado = phoneProductsData.toProductEntity()

        //then,  deberia dar este resultado

        assertEquals(id,resultado.id)
        assertEquals(name,resultado.name)
        assertEquals(price,resultado.price)
        assertEquals(image,resultado.image)
    }
}