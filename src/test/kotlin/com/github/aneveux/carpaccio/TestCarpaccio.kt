package com.github.aneveux.carpaccio

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestCarpaccio {

    @Test
    fun testHello() {
        assertThat(true).isTrue()
    }

    @Test
    fun testOrderValue() {
        assertThat(orderValue((1 to 10f))).isEqualTo(10f)
        assertThat(orderValue((10 to 10f))).isEqualTo(100f)
        assertThat(orderValue((2 to 3.14f))).isEqualTo(6.28f)
    }

    @Test
    fun testTaxedPriceUT() {
        assertThat(applyTax(10f to "UT")).isEqualTo(10.684999f)
        assertThat(applyTax(1f to "UT")).isEqualTo(1.0685f)
        assertThat(applyTax(4.32f to "UT")).isEqualTo(4.61592f)
    }

    @Test
    fun testTaxedPriceNV() {
        assertThat(applyTax(10f to "NV")).isEqualTo(10.8f)
        assertThat(applyTax(1f to "NV")).isEqualTo(1.08f)
        assertThat(applyTax(4.32f to "NV")).isEqualTo(4.6656003f)
    }

    @Test
    fun testTaxedPriceTX() {
        assertThat(applyTax(10f to "TX")).isEqualTo(10.625f)
        assertThat(applyTax(1f to "TX")).isEqualTo(1.0625f)
        assertThat(applyTax(4.32f to "TX")).isEqualTo(4.59f)
    }

    @Test
    fun testTaxedPriceAL() {
        assertThat(applyTax(10f to "AL")).isEqualTo(10.4f)
        assertThat(applyTax(1f to "AL")).isEqualTo(1.04f)
        assertThat(applyTax(4.32f to "AL")).isEqualTo(4.4928f)
    }

    @Test
    fun testTaxedPriceCA() {
        assertThat(applyTax(10f to "CA")).isEqualTo(10.825f)
        assertThat(applyTax(1f to "CA")).isEqualTo(1.0825f)
        assertThat(applyTax(4.32f to "CA")).isEqualTo(4.6764f)
    }

    @Test
    fun testDiscountPrice() {
        assertThat(applyDiscount(999f)).isEqualTo(999f)
        assertThat(applyDiscount(1000f)).isEqualTo(970.0f)
        assertThat(applyDiscount(3001f)).isEqualTo(2910.97f)
        assertThat(applyDiscount(5432.32f)).isEqualTo(5160.7036f)
        assertThat(applyDiscount(7883.21f)).isEqualTo(7331.3853f)
        assertThat(applyDiscount(10000.12f)).isEqualTo(9000.108f)
        assertThat(applyDiscount(55000f)).isEqualTo(46750.0f)
    }

    @Test
    fun testRound() {
        assertThat(round(10.23456f)).isEqualTo(10.23f)
        assertThat(round(10f)).isEqualTo(10f)
        assertThat(round(10.555f)).isEqualTo(10.56f)
    }

    @Test
    fun testFormat() {
        assertThat(format(12345.42f)).isEqualTo("$ 12,345.42")
        assertThat(format(123f)).isEqualTo("$ 123.00")
        assertThat(format(1234567.12f)).isEqualTo("$ 1,234,567.12")
    }

}