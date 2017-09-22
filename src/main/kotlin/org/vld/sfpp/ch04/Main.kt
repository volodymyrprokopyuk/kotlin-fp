package org.vld.sfpp.ch04

/**
 * Generates lazy Fibonacci sequence
 */
fun fibonacciSequence(): Sequence<Int> = generateSequence(0 to 1) { (prev, curr) -> curr to (prev + curr) }.map { it.first }

/**
 * Generates lazy sequence of prime numbers using Eratosthenes Sieve algorithm
 */
// start from 2 (the first prime number) with odd numbers greater than 2 that are not multiples of 2
fun eratosthenesSieve(): Sequence<Int> = generateSequence(2 to generateSequence(3) { it + 2 }) { (_, seq) ->
    // inner sequence head is the next prime number
    val nextPrime = seq.take(1).toList().first()
    // drop the next prime number from the inner sequence and
    // retain in the inner sequence only the numbers which are not multiples of the next prime number
    nextPrime to seq.drop(1).filter { it % nextPrime != 0 }
// collect resulting prime numbers from the outer sequence of pairs (prime number, filtered sequence for further analysis)
}.map { it.first }

fun main(args: Array<String>) {
    val fibonacci = fibonacciSequence().take(25).toList()
    println(fibonacci)
    val primes = eratosthenesSieve().take(25).toList()
    println(primes)
}
