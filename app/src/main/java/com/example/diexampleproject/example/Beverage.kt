package com.example.diexampleproject.example

interface Beverage {
    fun drink()
}

class CafeLatte: Beverage {
    override fun drink() {
        print("yammy")
    }
}

class CoolLimePizzo: Beverage {
    override fun drink() {
        print("soooooo good")
    }
}