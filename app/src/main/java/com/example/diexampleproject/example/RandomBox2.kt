package com.example.diexampleproject.example

class RandomBox2(
    random: RandomInt
) {
    private val score = random()

    fun unBoxing() = if (score >= 90) {
        Goods
    } else {
        null
    }
}