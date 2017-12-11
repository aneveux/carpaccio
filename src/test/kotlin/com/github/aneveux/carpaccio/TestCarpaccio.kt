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
        assertThat(orderValue(1, 10f)).isEqualTo(10f)
        assertThat(orderValue(10, 10f)).isEqualTo(100f)
        assertThat(orderValue(2, 3.14f)).isEqualTo(6.28f)
    }

}