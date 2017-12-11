package com.github.aneveux.carpaccio

import java.util.*

fun main(args: Array<String>) {
    println("Hello Carpaccio!")

    val input = Scanner(System.`in`)
    val items = askFor("items") { input.nextInt() }
    val price = askFor("price") { input.nextFloat() }

    val orderValue = orderValue(items, price)

    println("Order Value = $orderValue")
}

fun orderValue(items: Int, price: Float) = items * price

fun <T> askFor(something: String, provider: () -> T): T {
    println("$something:")
    return provider.invoke()
}