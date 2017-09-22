package org.vld.sfpp.ch04

/**
 * Generates lazy Fibonacci sequence
 */
fun fibonacciSequence(): Sequence<Int> = generateSequence(0 to 1) { (prev, curr) -> curr to (prev + curr) }.map { it.first }

fun main(args: Array<String>) {
    val res = fibonacciSequence().take(25).toList()
    println(res)
}