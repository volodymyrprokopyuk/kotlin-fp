package org.vld.fp.sfpp.ch04

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @Test
    @DisplayName("When fibonacciSequence. Then return the Fibonacci sequence")
    fun whenFibonacciSequence_thenReturnFibonacciSequence() {
        // Given & When
        val fibonacci = fibonacciSequence().take(14).toList()
        // Then
        val expectedFibonacci = listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233)
        assertThat(fibonacci).isEqualTo(expectedFibonacci)
    }

    @Test
    @DisplayName("When eratosthenesSieve. Then return the sequence of prime numbers")
    fun whenEratosthenesSieve_thenReturnPrimeNumbersSequence() {
        // Given & When
        val primes = eratosthenesSieve().take(14).toList()
        // Then
        val expectedPrimes = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43)
        assertThat(primes).isEqualTo(expectedPrimes)
    }

}
