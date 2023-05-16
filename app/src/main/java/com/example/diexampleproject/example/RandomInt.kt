package com.example.diexampleproject.example

import kotlin.random.Random

interface RandomInt {
    operator fun invoke(): Int
}

class RandomIntImpl: RandomInt {
    override fun invoke() = Random(System.currentTimeMillis()).nextInt(100)
}

class RandomIntTestImpl: RandomInt {
    override fun invoke() = 90
}