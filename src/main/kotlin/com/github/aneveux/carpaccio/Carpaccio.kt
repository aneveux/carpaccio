package com.github.aneveux.carpaccio

import java.math.RoundingMode
import java.text.DecimalFormat
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

    (items to price).orderValue()
            .then("Applying Discount", ::applyDiscount)
            .to(state)
            .then("Applying Taxes", ::applyTax)
            .then("Rounding Price", ::round)
            .then("Formatting Price", ::format)
            .then("Final Price") { "\$ $it" }
}

fun Pair<Int, Float>.orderValue(): Float = (this.first * this.second).also { println("Order Value = $it") }

fun applyDiscount(price: Float) = price -
                                  price * when {
                                      price >= 50_000 -> 0.15f
                                      price >= 10_000 -> 0.1f
                                      price >= 7_000 -> 0.07f
                                      price >= 5_000 -> 0.05f
                                      price >= 1_000 -> 0.03f
                                      else -> 0f
                                  }

fun applyTax(priceInState: Pair<Float, String>) = priceInState.first + priceInState.first * taxes.getOrElse(priceInState.second) { 0f }

fun round(n: Float): Float {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_EVEN
    return df.format(n)?.toFloat() ?: n
}

fun format(n: Float) = DecimalFormat("#,###.00").format(n) ?: n.toString()

fun <T, R> T.then(action: String, f: (T) -> R) =
        f.invoke(this).also { println("$action: $it") }

fun <T> askFor(something: String, provider: () -> T): T {
    println("$something:")
    return provider.invoke()
}