package com.example.sprint6

import com.example.sprint6.data.local.PhoneDetailsEntity
import com.example.sprint6.data.local.PhoneProductsEntity
import com.example.sprint6.data.remoto.PhoneDetailsData
import com.example.sprint6.data.remoto.PhoneProductsData

fun PhoneDetailsData.toDetaislEntity(): PhoneDetailsEntity = PhoneDetailsEntity(
    this.id,
    this.name,
    this.price,
    this.image,
    this.description,
    this.lastPrice,
    this.credit
)

fun PhoneProductsData.toProductEntity(): PhoneProductsEntity = PhoneProductsEntity(
    this.id,
    this.name,
    this.price,
    this.image
)

/*
esta funcion resive un entero, lo convierte en un string y luego invierte el orden
para poder usar un forEach y cada 3 caracteres agregar un punto siempre y cuando la cantidad de
caracteres sea igual a la posicion del punto y diferente al largo del string menos 1
*/
fun Int.toPesos(): String {
    var intToString = this.toString().reversed()

    var positionPoint = 2
    var newPesos: String = ""
    var cont = 0

    intToString.forEach { char ->

        newPesos += char
        if (cont == positionPoint && positionPoint!=intToString.length -1) {
            newPesos += "."
            positionPoint += 3

        }
        cont++
    }

    return newPesos.reversed()
}
