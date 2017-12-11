package com.github.aneveux.carpaccio

import java.util.*

val taxes = mapOf(
        "UT" to 0.0685f,
        "NV" to 0.08f,
        "TX" to 0.0625f,
        "AL" to 0.04f,
        "CA" to 0.0825f
)

fun main(args: Array<String>) {
    println("Hello Carpaccio!")

    val input = Scanner(System.`in`)
    val items = askFor("items") { input.nextInt() }
    val price = askFor("price") { input.nextFloat() }
    val state = askFor("state") { input.next() }

    val orderValue = orderValue(items, price)

    println("Order Value = $orderValue")

    val taxedPrice = applyTax(orderValue,state)

    println("Price for UT = $taxedPrice")
}

fun orderValue(items: Int, price: Float) = items * price

fun applyTax(price: Float, state: String) = price + price * taxes.getOrElse(state) { 0f }

fun <T> askFor(something: String, provider: () -> T): T {
    println("$something:")
    return provider.invoke()
}