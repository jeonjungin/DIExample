package com.example.diexampleproject.example

class JungIn(private val beverages: List<Beverage>) {

    fun work() {
        beverages.forEach {
            it.drink()
        }
        // ...
    }
}