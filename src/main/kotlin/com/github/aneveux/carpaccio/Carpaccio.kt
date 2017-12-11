package com.github.aneveux.carpaccio

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

val taxes = mapOf("UT" to 0.0685f, "NV" to 0.08f, "TX" to 0.0625f, "AL" to 0.04f, "CA" to 0.0825f)

val discounts = TreeMap(
        mapOf(50_000 to 0.15f, 10_000 to 0.1f, 7_000 to 0.07f, 5_000 to 0.05f, 1_000 to 0.03f, 0 to 0f))

fun main(args: Array<String>) {
    println("Hello Carpaccio!")

    val input = Scanner(System.`in`)
    val items = askFor("items") { input.nextInt() }
    val price = askFor("price") { input.nextFloat() }
    val state = askFor("state") { input.next() }

    (items to price)
            .then("Calculating Order Value", ::orderValue)
            .then("Applying Discount", ::applyDiscount)
            .to(state)
            .then("Applying Taxes", ::applyTax)
            .then("Rounding Price", ::round)
            .then("Formatting Price", ::format)
}

fun orderValue(itemsAndPrice: Pair<Int, Float>) = (itemsAndPrice.first * itemsAndPrice.second)

fun applyDiscount(price: Float) = price - price * discounts.floorEntry(price.toInt()).value

fun applyTax(priceInState: Pair<Float, String>) = priceInState.first +
                                                  priceInState.first * taxes.getOrElse(priceInState.second) { 0f }

fun round(n: Float): Float {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_EVEN
    return df.format(n)?.toFloat() ?: n
}

fun format(n: Float) = DecimalFormat("#,###.00").format(n)?.let { "\$ $it" } ?: "\$ $n"

fun <T, R> T.then(action: String, f: (T) -> R) =
        f.invoke(this).also { println("$action: $it") }

fun <T> askFor(something: String, provider: () -> T): T {
    println("$something:")
    return provider.invoke()
}