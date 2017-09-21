package org.vld.sfpp.ch04

fun fibonacciSequence(): Sequence<Int> = generateSequence(0 to 1) { prev, curr -> curr to (prev + curr) }.map { it.first }

fun main(args: Array<String>) {
    val numberSequence = generateSequence(0) { it + 1 }.map { it * 2 }.filter { it % 2 == 0 }
    val res = numberSequence.take(25).toList()
    println(res)
}