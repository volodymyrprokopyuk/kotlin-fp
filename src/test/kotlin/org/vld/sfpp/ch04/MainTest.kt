package org.vld.sfpp.ch04

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    @DisplayName("When fibonacciSequence. Then return Fibonacci sequence")
    fun whenFibonacciSequence_thenReturnFibonacciSequence() {
        // Given & When
        val fibonacci = fibonacciSequence().take(12).toList()
        // Then
        val expectedFibonacci = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
        assertThat(fibonacci).isEqualTo(expectedFibonacci)
    }

}