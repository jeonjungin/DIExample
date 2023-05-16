package com.example.diexampleproject.example

fun main() {
    val goods1 = RandomBox().unBoxing()
    val goods2 = RandomBox2(RandomIntImpl()).unBoxing()
    val goods3 = RandomBox2(RandomIntTestImpl()).unBoxing()
    // ...
}