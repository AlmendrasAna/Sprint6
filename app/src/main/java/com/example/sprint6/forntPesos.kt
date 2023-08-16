package com.example.sprint6

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

