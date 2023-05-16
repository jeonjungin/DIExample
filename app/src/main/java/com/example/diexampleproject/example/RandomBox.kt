package com.example.diexampleproject.example

import kotlin.random.Random

object Goods

class RandomBox {
    private val score = Random(System.currentTimeMillis()).nextInt(100)

    fun unBoxing() = if (score >= 90) {
        Goods
    } else {
        null
    }
}