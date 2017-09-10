package org.vld.sfpp.ch01

fun letterFrequency(str: String) = str
        .filter(Char::isLetter)
        .groupBy { it }
        .map { (letter, occurrences) -> letter to occurrences.size }
        .toMap()

fun main(args: Array<String>) {
    val res = letterFrequency("hello world!")
    println(res)
}
